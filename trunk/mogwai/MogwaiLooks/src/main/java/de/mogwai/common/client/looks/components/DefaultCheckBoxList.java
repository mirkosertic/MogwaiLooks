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

import java.util.List;
import java.util.Vector;

import de.mogwai.common.client.looks.components.renderer.DefaultListSelectableItemEditor;
import de.mogwai.common.client.looks.components.renderer.DefaultListSelectableItemRenderer;
import de.mogwai.common.client.looks.tools.DefaultSelectableItem;

public class DefaultCheckBoxList<Typ> extends DefaultTable {

	private DefaultCheckBoxListModel<Typ> model = new DefaultCheckBoxListModel<Typ>();

	public DefaultCheckBoxList() {
		initialize();
	}

	private void initialize() {
		setModel(model);
		getTableHeader().setVisible(false);
		setShowGrid(false);
		setRowSelectionAllowed(true);
		getColumnModel().getColumn(0).setCellEditor(
				new DefaultListSelectableItemEditor(this));
		getColumnModel().getColumn(0).setCellRenderer(
				DefaultListSelectableItemRenderer.getInstance());
	}

	@Override
	public DefaultCheckBoxListModel<Typ> getModel() {
		return model;
	}

	public void setSelectedItems(List<Typ> selected) {

		for (int i = 0; i < model.getRowCount(); i++) {
			DefaultSelectableItem<Typ> item = (DefaultSelectableItem<Typ>) model
					.getRow(i);
			item.setSelected(false);

			for (Typ theTyp : selected) {
				if (theTyp.equals(item.getValue())) {
					item.setSelected(true);
				}
			}
		}
		getModel().fireTableDataChanged();
	}

	public List<Typ> getSelectedItems() {
		Vector<Typ> selected = new Vector<Typ>();
		for (int i = 0; i < model.getRowCount(); i++) {
			DefaultSelectableItem<Typ> item = (DefaultSelectableItem<Typ>) model
					.getRow(i);
			if (item.isSelected()) {
				selected.add(item.getValue());
			}
		}

		return selected;
	}

}
