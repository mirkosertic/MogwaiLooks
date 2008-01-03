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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import de.mogwai.common.client.looks.UIInitializer;

public class DefaultTable extends JTable {

	private DefaultScrollPane scrollPane;

	private DefaultPopupMenu contextMenu;

	public DefaultTable() {

		scrollPane = new DefaultScrollPane(this);
		initialize();
	}

	public JScrollPane getScrollPane() {

		return scrollPane;
	}

	public DefaultPopupMenu getContextMenu() {

		return contextMenu;
	}

	public void setContextMenu(DefaultPopupMenu contaxtMenu) {

		this.contextMenu = contaxtMenu;
		UIInitializer.getInstance().initialize(contaxtMenu);
	}

	private void initialize() {

		setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
		setCellSelectionEnabled(false);
		setColumnSelectionAllowed(false);
		setRowSelectionAllowed(true);
		setGridColor(UIInitializer.getInstance().getDefaultTableGridColor());

		MouseListener theListener = new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (((e.getButton() & MouseEvent.BUTTON2) > 0)
						&& (contextMenu != null)) {
					contextMenu.show(DefaultTable.this, e.getX(), e.getY());
				}
			}
		};

		addMouseListener(theListener);
		scrollPane.addMouseListener(theListener);
	}
}
