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
package de.mogwai.common.client.looks.components.table;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import de.mogwai.binding.tools.BeanUtils;
import de.mogwai.common.client.looks.tools.ModificationTrackerHelper;
import de.mogwai.i18n.ResourceHelper;
import de.mogwai.i18n.ResourceHelperProvider;

public class DefaultTableDescriptor {

	private Vector<DefaultTableColumnDescriptor> column = new Vector<DefaultTableColumnDescriptor>();

	protected ResourceHelper resourceHelper;

	private JTable table;

	protected DefaultTableDescriptor(ResourceHelper aResourceHelper) {

		resourceHelper = aResourceHelper;
	}

	protected DefaultTableDescriptor(ResourceHelperProvider aProvider) {

		this(aProvider.getResourceHelper());
	}

	protected DefaultTableColumnDescriptor addColumn(String displayName,
			Class clazz, String property) {
		return addColumn(displayName, clazz, property, -1);
	}

	protected DefaultTableColumnDescriptor addColumn(String displayName,
			Class clazz, String property, boolean editable) {

		return addColumn(displayName, clazz, property, -1, editable);
	}

	protected DefaultTableColumnDescriptor addColumn(String aResourceKey,
			Class clazz, String property, int size) {
		return addColumn(aResourceKey, clazz, property, size, false);
	}

	protected DefaultTableColumnDescriptor addColumn(String aResourceKey,
			Class clazz, String property, int size, boolean editable) {

		DefaultTableColumnDescriptor theDesc = new DefaultTableColumnDescriptor(
				resourceHelper.getText(aResourceKey), clazz, property, size,
				editable);
		column.add(theDesc);

		return theDesc;
	}

	public int getColumnCount() {

		return column.size();
	}

	public String getColumnName(int columnIndex) {

		return column.get(columnIndex).getDisplayName();
	}

	public Class getColumnClass(int columnIndex) {

		return column.get(columnIndex).getClass();
	}

	public Object getValue(Object aRow, int columnIndex) {

		try {
			return BeanUtils.getProperty(aRow, column.get(columnIndex)
					.getPropertyName());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setValue(Object aRow, int columnIndex, Object aValue) {

		try {
			BeanUtils.setProperty(aRow, column.get(columnIndex)
					.getPropertyName(), aValue);
			ModificationTrackerHelper.setModified(table, true);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isColumnEditable(int columnIndex) {

		return column.get(columnIndex).isEditable();
	}

	public void configureTable(JTable aTable) {

		table = aTable;

		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setRowSelectionAllowed(true);
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.getTableHeader().setReorderingAllowed(false);
		for (DefaultTableColumnDescriptor theDesc : column) {
			int index = column.indexOf(theDesc);
			TableColumn theColumn = table.getColumnModel().getColumn(index);
			if (theDesc.getSize() > 0) {
				theColumn.setMinWidth(theDesc.getSize());
				theColumn.setMaxWidth(theDesc.getSize());
				theColumn.setWidth(theDesc.getSize());
			}
			theColumn.setCellRenderer(theDesc.getRenderer());
			if (theDesc.getEditor() != null)
				theColumn.setCellEditor(theDesc.getEditor());
		}
	}
}
