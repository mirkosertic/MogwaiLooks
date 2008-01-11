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

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import de.mogwai.common.client.looks.tools.ModificationTracker;
import de.mogwai.common.client.looks.tools.ModificationTrackerHelper;

public class DefaultFrameContent extends DefaultPanel implements
		ModificationTracker, ToolbarProvider {

	protected StatusBar statusBar;

	protected DefaultToolbar toolbar = new DefaultToolbar();

	protected boolean modified;

	public DefaultFrameContent() {

		initialize();
	}

	public void initializeBindingInfo() {
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}
	
	protected void initializeToolbar() {
	}

	private void initialize() {

		statusBar = createStatusBar();

		setBorder(null);
		setLayout(new BorderLayout());
		add(toolbar, BorderLayout.NORTH);
		add((Component) statusBar, BorderLayout.SOUTH);
	}

	public void setDetailComponent(JComponent aComponent) {

		add(aComponent, BorderLayout.CENTER);
	}

	public boolean isModified() {

		return modified;
	}

	public void setModified(boolean modified) {

		this.modified = modified;

		ModificationTrackerHelper.setModified(getParent(), modified);
	}

	public String getTitle() {

		return "";
	}

	public DefaultToolbar getToolbar() {
		return toolbar;
	}

	public void doExit(ActionEvent e) {
		Container theParent = this.getParent();
		while (theParent != null) {
			if (theParent instanceof JInternalFrame) {
				theParent.setVisible(false);
				return;
			}
			if (theParent instanceof JFrame) {
				theParent.setVisible(false);
				return;
			}
			if (theParent instanceof JDialog) {
				theParent.setVisible(false);
				return;
			}

			theParent = theParent.getParent();
		}
	}

	public StatusBar createStatusBar() {
		return new DefaultStatusBar();
	}
}
