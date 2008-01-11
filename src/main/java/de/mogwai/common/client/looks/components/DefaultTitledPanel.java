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
package de.mogwai.common.client.looks.components;

import java.awt.event.ActionEvent;

import de.mogwai.common.client.looks.components.action.ActionEventProcessor;
import de.mogwai.common.client.looks.components.action.ActionEventProcessorHelper;
import de.mogwai.common.i18n.I18NAble;
import de.mogwai.common.i18n.ResourceHelper;
import de.mogwai.common.i18n.ResourceHelperLocator;

public class DefaultTitledPanel extends DefaultPanel implements I18NAble,
		ActionEventProcessor {

	private DefaultTitledBorder border;

	private String resourceID;

	public DefaultTitledPanel(String titleResourceID) {

		initialize(titleResourceID);
	}

	private void initialize(String titleResouceID) {

		border = new DefaultTitledBorder(titleResouceID);
		setBorder(border);
		resourceID = titleResouceID;
	}

	public ResourceHelper getResourceHelper() {

		return ResourceHelperLocator.findResourceHelperFor(this);
	}

	public String getResourceBundleID() {

		return resourceID;
	}

	public void setText(String aText) {

		border.setTitle(aText);
	}

	public void processActionEvent(ActionEvent e) {
		ActionEventProcessorHelper.invoke(this, e);
	}
}
