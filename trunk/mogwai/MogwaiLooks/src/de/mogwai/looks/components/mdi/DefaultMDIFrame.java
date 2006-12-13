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
package de.mogwai.looks.components.mdi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import de.mogwai.looks.UIInitializer;
import de.mogwai.looks.components.DefaultFrame;
import de.mogwai.looks.components.action.ActionEventProcessor;
import de.mogwai.looks.components.action.ActionEventProcessorHelper;
import de.mogwai.looks.components.action.DefaultAction;
import de.mogwai.looks.components.menu.DefaultMenu;
import de.mogwai.looks.components.menu.DefaultMenuItem;

public class DefaultMDIFrame extends DefaultFrame implements
		ActionEventProcessor {

	private DefaultDesktopPane desktopPane = new DefaultDesktopPane();

	private DefaultMenu windowMenu = new DefaultMenu("WINDOWS");

	private DefaultAction cascadeAction = new DefaultAction(this, this,
			"CASCADE_WINDOWS");

	public DefaultMDIFrame() {

		this(null);
	}

	public DefaultMDIFrame(String aResourceID) {

		super(aResourceID);
		initialize();
	}

	private void initialize() {

		cascadeAction.setCommandName("Cascade");

		getDefaultFrameContent()
				.setDetailComponent(desktopPane.getScrollPane());
		menuBar.add(windowMenu);

		windowMenu.addMenuListener(new MenuListener() {

			public void menuSelected(MenuEvent e) {

				windowMenu.removeAll();
				// windowMenu.add(windowCascade);
				windowMenu.add(new DefaultMenuItem(cascadeAction));
				Vector<DefaultInternalFrame> theFrames = desktopPane
						.getChilds();
				if (theFrames.size() > 0) {
					windowMenu.addSeparator();
					for (final DefaultInternalFrame theFrame : theFrames) {
						DefaultMenuItem theItem = new DefaultMenuItem(theFrame
								.getTitle());
						theItem.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {

								theFrame.activate();
							}
						});
						UIInitializer.getInstance().initializeFontAndColors(
								theItem);
						windowMenu.add(theItem);
					}
				}
			}

			public void menuDeselected(MenuEvent e) {

			}

			public void menuCanceled(MenuEvent e) {

			}
		});
	}

	public void addChild(DefaultInternalFrame aFrame) {

		desktopPane.add(aFrame);
		aFrame.activate();
	}

	public void cascadeChilds() {

		desktopPane.cascade();
	}

	public String getBaseTitle() {

		return getTitle();
	}

	public void activateChild(DefaultInternalFrame aFrame) {

		setTitle(getBaseTitle() + " - " + aFrame.getTitle());
	}

	public void restoreDefaultTitle() {

		setTitle(getBaseTitle());
	}

	public void doCascade(ActionEvent e) {
		desktopPane.cascade();
	}

	public void processActionEvent(ActionEvent e) {
		ActionEventProcessorHelper.invoke(this, e);
	}
}
