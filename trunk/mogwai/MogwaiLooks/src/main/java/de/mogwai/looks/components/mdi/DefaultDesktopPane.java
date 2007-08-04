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

import java.awt.Component;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import de.mogwai.looks.components.DefaultScrollPane;

public class DefaultDesktopPane extends JDesktopPane {

	private DefaultScrollPane scrollPane;

	private Vector<DefaultInternalFrame> frames = new Vector<DefaultInternalFrame>();

	public DefaultDesktopPane() {

		scrollPane = new DefaultScrollPane(this);
		setDesktopManager(new DefaultDesktopManager(this));
	}

	public JScrollPane getScrollPane() {

		return scrollPane;
	}

	private void removeInternal(DefaultInternalFrame aFrame) {

		frames.remove(aFrame);
	}

	public void remove(DefaultInternalFrame aFrame) {

		removeInternal(aFrame);
		super.remove(aFrame);
	}

	protected DefaultMDIFrame getMDIFrame() {

		Component theRoot = SwingUtilities.getRoot(this);
		if (theRoot instanceof DefaultMDIFrame)
			return (DefaultMDIFrame) theRoot;
		return null;
	}

	protected void handleChildClosing(DefaultInternalFrame aFrame) {

		if (aFrame.isSelected())
			getMDIFrame().restoreDefaultTitle();
		removeInternal(aFrame);
	}

	protected void handleChildActivated(DefaultInternalFrame aFrame) {

		getMDIFrame().activateChild(aFrame);
	}

	public void add(DefaultInternalFrame aFrame) {

		frames.add(aFrame);
		aFrame.setLocation(10, 10);
		super.add(aFrame);
		aFrame.addInternalFrameListener(new InternalFrameListener() {

			public void internalFrameOpened(InternalFrameEvent e) {

				handleChildActivated((DefaultInternalFrame) e
						.getInternalFrame());
			}

			public void internalFrameClosing(InternalFrameEvent e) {

				handleChildClosing((DefaultInternalFrame) e.getInternalFrame());
			}

			public void internalFrameClosed(InternalFrameEvent e) {

			}

			public void internalFrameIconified(InternalFrameEvent e) {

			}

			public void internalFrameDeiconified(InternalFrameEvent e) {

			}

			public void internalFrameActivated(InternalFrameEvent e) {

				handleChildActivated((DefaultInternalFrame) e
						.getInternalFrame());
			}

			public void internalFrameDeactivated(InternalFrameEvent e) {

			}
		});
	}

	public Vector<DefaultInternalFrame> getChilds() {

		return frames;
	}

	public void cascade() {

		int x = 10;
		int y = 10;
		DefaultInternalFrame theSelected = (DefaultInternalFrame) getSelectedFrame();
		if (theSelected != null) {
			frames.remove(theSelected);
			frames.add(theSelected);
		}
		for (DefaultInternalFrame theFrame : frames) {
			theFrame.setLocation(x, y);
			x += 20;
			y += 20;
			theFrame.toFront();
		}
	}

	/**
	 * Sets all component size properties ( maximum, minimum, preferred) to the
	 * given dimension.
	 */
	public void setAllSize(Dimension d) {

		setMinimumSize(d);
		setMaximumSize(d);
		setPreferredSize(d);
	}

	/**
	 * Sets all component size properties ( maximum, minimum, preferred) to the
	 * given width and height.
	 */
	public void setAllSize(int width, int height) {

		setAllSize(new Dimension(width, height));
	}
}
