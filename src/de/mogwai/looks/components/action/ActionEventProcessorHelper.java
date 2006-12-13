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

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.lang.reflect.Method;

import javax.swing.JRootPane;
import javax.swing.RootPaneContainer;

public class ActionEventProcessorHelper {

	public static JRootPane getRootPane(Object aWhere) {
		if (!(aWhere instanceof Component))
			return null;

		if (aWhere instanceof RootPaneContainer)
			return (JRootPane) ((RootPaneContainer) aWhere).getRootPane();

		return getRootPane(((Component) aWhere).getParent());
	}

	public static void invoke(final Object aWhere, final ActionEvent aEvent) {

		final JRootPane theRootPane = getRootPane(aWhere);
		if (theRootPane != null) {
			theRootPane.getGlassPane().setCursor(
					Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			theRootPane.getGlassPane().setVisible(true);
		}

		SwingWorker3 theWorker = new SwingWorker3() {

			@Override
			public Object construct() {

				try {
					Method theMethod = aWhere.getClass().getMethod(
							"do" + aEvent.getActionCommand(),
							new Class[] { ActionEvent.class });
					theMethod.invoke(aWhere, new Object[] { aEvent });
				} catch (Exception ex) {
					ex.printStackTrace();
				}

				return null;
			}

			@Override
			public void finished() {
				super.finished();

				if (theRootPane != null) {
					theRootPane.getGlassPane().setCursor(
							Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					theRootPane.getGlassPane().setVisible(false);
				}
			}

		};
		theWorker.start();
	}
}
