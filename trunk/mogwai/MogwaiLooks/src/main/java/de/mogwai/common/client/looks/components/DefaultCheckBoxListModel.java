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

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import de.mogwai.common.client.looks.tools.DefaultSelectableItem;

public class DefaultCheckBoxListModel<Typ> implements TableModel {

	protected List<DefaultSelectableItem<Typ>> data;

	protected Vector<TableModelListener> listener = new Vector<TableModelListener>();

	public DefaultCheckBoxListModel() {
		data = new Vector<DefaultSelectableItem<Typ>>();
	}

	public int getRowCount() {

		return data.size();
	}

	public int getColumnCount() {
		return 1;
	}

	public String getColumnName(int columnIndex) {
		return "";
	}

	public Class<?> getColumnClass(int columnIndex) {
		return DefaultSelectableItem.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}

	public Object getRow(int rowIndex) {
		return data.get(rowIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex);
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	}

	public void fireTableDataChanged() {

		TableModelEvent theEvent = new TableModelEvent(this);
		for (TableModelListener theListener : listener) {
			theListener.tableChanged(theEvent);
		}
	}

	public void add(Typ aEntry) {
		data.add(new DefaultSelectableItem(aEntry));
		fireTableDataChanged();
	}

	public void addAll(List<Typ> aList) {
		for (Typ theTyp : aList) {
			data.add(new DefaultSelectableItem(theTyp));
		}
		fireTableDataChanged();
	}

	public void setData(List<DefaultSelectableItem<Typ>> aData) {

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
}