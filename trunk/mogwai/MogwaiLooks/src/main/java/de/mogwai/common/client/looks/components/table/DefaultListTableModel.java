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

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class DefaultListTableModel<Typ> implements TableModel {

	protected List<Typ> data;

	protected Vector<TableModelListener> listener = new Vector<TableModelListener>();

	protected DefaultTableDescriptor tableDescriptor;

	public DefaultListTableModel(DefaultTableDescriptor adescriptor) {

		data = new Vector<Typ>();
		tableDescriptor = adescriptor;
	}

	public int getRowCount() {

		return data.size();
	}

	public int getColumnCount() {

		return tableDescriptor.getColumnCount();
	}

	public String getColumnName(int columnIndex) {

		return tableDescriptor.getColumnName(columnIndex);
	}

	public Class<?> getColumnClass(int columnIndex) {

		return tableDescriptor.getColumnClass(columnIndex);
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return tableDescriptor.isColumnEditable(columnIndex);
	}

	public Typ get(int aIndex) {
		return data.get(aIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		Typ theRow = data.get(rowIndex);
		return tableDescriptor.getValue(theRow, columnIndex);
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

		Typ theRow = data.get(rowIndex);
		tableDescriptor.setValue(theRow, columnIndex, aValue);
	}

	public void fireTableDataChanged() {

		TableModelEvent theEvent = new TableModelEvent(this);
		for (TableModelListener theListener : listener) {
			theListener.tableChanged(theEvent);
		}
	}

	public void add(Typ aEntry) {

		data.add(aEntry);
		fireTableDataChanged();
	}

	public void remove(Typ aEntry) {
		data.remove(aEntry);
		fireTableDataChanged();
	}

	public void remove(int aIndex) {
		data.remove(aIndex);
		fireTableDataChanged();
	}

	public void setData(List<Typ> aData) {

		data = aData;
		fireTableDataChanged();
	}

	public void addTableModelListener(TableModelListener l) {

		listener.add(l);
	}

	public void removeTableModelListener(TableModelListener l) {

		listener.remove(l);
	}

	public void clear() {

		data.clear();
		fireTableDataChanged();
	}

	public void configureTable(JTable aTable) {
		tableDescriptor.configureTable(aTable);
	}

	public boolean isEmpty() {
		return data.isEmpty();
	}

	public boolean contains(Typ o) {
		return data.contains(o);
	}

	public Iterator<Typ> iterator() {
		return data.iterator();
	}
}