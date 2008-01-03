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
package de.mogwai.common.client.looks.components.renderer;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.table.TableCellRenderer;

import de.mogwai.common.client.looks.UIInitializer;
import de.mogwai.common.client.looks.tools.DefaultSelectableItem;

public class DefaultListSelectableItemRenderer extends DefaultRenderer
		implements ListCellRenderer, TableCellRenderer {

	private JCheckBox checkBox = new JCheckBox();

	private UIInitializer initializer = UIInitializer.getInstance();

	private static DefaultListSelectableItemRenderer me = new DefaultListSelectableItemRenderer();

	public static DefaultListSelectableItemRenderer getInstance() {

		return me;
	}

	private DefaultListSelectableItemRenderer() {

		initializer = UIInitializer.getInstance();
		initializer.initializeComponent(checkBox);
	}

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {

		DefaultSelectableItem theItem = (DefaultSelectableItem) value;
		checkBox.setText(objectToString(theItem.getValue()));
		checkBox.setSelected(theItem.isSelected());
		if (isSelected) {
			checkBox.setBackground(initializer
					.getDefaultListSelectionBackground());
			checkBox.setForeground(initializer
					.getDefaultListSelectionForeground());
		} else {
			checkBox.setBackground(initializer
					.getDefaultListNonSelectionBackground());
			checkBox.setForeground(initializer
					.getDefaultListNonSelectionForeground());
		}
		return checkBox;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		return getListCellRendererComponent(null, value, row, isSelected,
				hasFocus);
	}
}
