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
package de.mogwai.common.client.looks.components.mdi;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import de.mogwai.common.client.looks.UIInitializer;
import de.mogwai.common.client.looks.components.DefaultFrameContent;
import de.mogwai.common.client.looks.tools.ModificationTracker;
import de.mogwai.common.i18n.I18NAble;
import de.mogwai.common.i18n.ResourceHelper;
import de.mogwai.common.i18n.ResourceHelperProvider;

public class DefaultInternalFrame extends JInternalFrame implements
		ResourceHelperProvider, I18NAble, ModificationTracker {

	private String resourceID;

	private ResourceHelper resource;

	private DefaultFrameContent content = new DefaultFrameContent();

	private boolean modified;

	public DefaultInternalFrame(String aBundleName, String aResourceID) {

		super(aResourceID);
		resourceID = aResourceID;
		resource = ResourceHelper.getResourceHelper(aBundleName);
		initialize();
		UIInitializer.getInstance().initialize(this);
	}

	public DefaultInternalFrame(String aBundleName, String aResourceID,
			DefaultFrameContent aContent) {

		this(aBundleName, aResourceID);
		content = aContent;
		setContentPane(content);
		UIInitializer.getInstance().initialize(this);
		content.initializeBindingInfo();
		pack();
	}

	public DefaultFrameContent getDefaultFrameContent() {

		return content;
	}

	private void initialize() {

		setVisible(true);
		setClosable(true);
		setContentPane(content);
		setBorder(BorderFactory.createLineBorder(UIInitializer.getInstance()
				.getDefaultInternalFrameBorderColor(), 1));

	}

	public void activate() {

		try {
			setSelected(true);
		} catch (Exception e) {
		}
	}

	public ResourceHelper getResourceHelper() {

		return resource;
	}

	public String getResourceBundleID() {

		return resourceID;
	}

	public void setText(String aText) {

		setTitle(aText);
	}

	public void setModified(boolean bModified) {
		modified = bModified;
		setClosable(!bModified);
	}

	public boolean isModified() {
		return modified;
	}

	@Override
	public void setVisible(boolean bStatus) {
		super.setVisible(bStatus);
		if (!bStatus) {
			InternalFrameEvent theEvent = new InternalFrameEvent(this,
					InternalFrameEvent.INTERNAL_FRAME_CLOSING);
			for (InternalFrameListener theListener : getListeners(InternalFrameListener.class)) {
				theListener.internalFrameClosing(theEvent);
			}
		}
	}
}
