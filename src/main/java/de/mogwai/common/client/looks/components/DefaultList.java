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

import javax.swing.JList;
import javax.swing.JScrollPane;

import de.mogwai.common.client.looks.components.list.DefaultListModel;
import de.mogwai.common.client.looks.components.renderer.DefaultListRenderer;

public class DefaultList<Typ> extends JList {

	private DefaultScrollPane scrollPane;

	private DefaultListModel<Typ> model;

	public DefaultList() {
		initialize();
	}

	public JScrollPane getScrollPane() {

		return scrollPane;
	}

	private void initialize() {

		scrollPane = new DefaultScrollPane(this);
		model = new DefaultListModel<Typ>();
		setCellRenderer(DefaultListRenderer.getInstance());
		setModel(model);
	}

	@Override
	public DefaultListModel<Typ> getModel() {

		return model;
	}
}
