/**
 * Mogwai Looks. Copyright (C) 2002 The Mogwai Project.
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
package de.mogwai.looks.components.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.AbstractAction;

import de.mogwai.i18n.I18NAble;
import de.mogwai.i18n.I18NInitializer;
import de.mogwai.i18n.ResourceHelper;
import de.mogwai.i18n.ResourceHelperProvider;
import de.mogwai.looks.tools.ImageIconCache;

public class DefaultAction extends AbstractAction implements I18NAble {

	private Vector<ActionListener> listener = new Vector<ActionListener>();

	private String resourceID;

	private ResourceHelper resourceHelper;

	private ActionEventProcessor processor;

	public DefaultAction(ActionEventProcessor aProcessor,
			ResourceHelperProvider aProvider, String aResourceID) {
		this(aProcessor, aProvider.getResourceHelper(), aResourceID);
	}

	public DefaultAction(ResourceHelperProvider aProvider, String aResourceID) {
		this(null, aProvider.getResourceHelper(), aResourceID);
	}

	public DefaultAction(ActionEventProcessor aProcessor, String aBundleName,
			String aResourceID) {
		this(aProcessor, ResourceHelper.getResourceHelper(aBundleName),
				aResourceID);
	}

	public DefaultAction(String aBundleName, String aResourceID) {
		this(null, ResourceHelper.getResourceHelper(aBundleName), aResourceID);
	}

	public DefaultAction(ActionEventProcessor aProcessor,
			ResourceHelper aHelper, String aResourceID) {

		super(aResourceID);
		resourceID = aResourceID;
		resourceHelper = aHelper;
		processor = aProcessor;
		initialize();
	}

	private void initialize() {

		putValue(NAME, null);
		
		String iconName = resourceHelper.getIcon(resourceID);
		if (iconName != null) {
			putValue(SMALL_ICON, ImageIconCache.getImageIcon(iconName));
		}
		
		try {
			I18NInitializer.initialize(this);
		} catch (Exception e) {
			//TODO: Add better exception handling here
		}
	}

	public void addActionListener(ActionListener aListener) {
		listener.add(aListener);
	}

	public void actionPerformed(ActionEvent e) {

		for (ActionListener theListener : listener) {
			theListener.actionPerformed(e);
		}

		if (processor != null)
			processor.processActionEvent(e);
	}

	public String getResourceBundleID() {
		return resourceID;
	}

	public void setText(String aText) {
		putValue(NAME, aText);
	}

	public void setCommandName(String aCommandName) {
		putValue(ACTION_COMMAND_KEY, aCommandName);
	}

	public ResourceHelper getResourceHelper() {
		return resourceHelper;
	}
}
