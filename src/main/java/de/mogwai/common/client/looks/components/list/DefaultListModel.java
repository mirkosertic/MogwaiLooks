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
package de.mogwai.common.client.looks.components.list;

import java.util.List;
import java.util.Vector;

import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class DefaultListModel<T> implements ListModel {

	protected List<T> data;

	protected Vector<ListDataListener> listener = new Vector<ListDataListener>();

	public DefaultListModel() {

		data = new Vector<T>();
	}

	public int getSize() {

		return data.size();
	}

	public Object getElementAt(int index) {

		return data.get(index);
	}

	public void addListDataListener(ListDataListener l) {

		listener.add(l);
	}

	public void removeListDataListener(ListDataListener l) {

		listener.remove(l);
	}

	public void fireListDataChanged() {

		ListDataEvent theEvent = new ListDataEvent(this,
				ListDataEvent.CONTENTS_CHANGED, 0, 0);
		for (ListDataListener theListener : listener) {
			theListener.contentsChanged(theEvent);
		}
	}

	public void add(T aEntry) {

		data.add(aEntry);
		fireListDataChanged();
	}
	
	public void remove(T aEntry) {
		data.remove(aEntry);
		fireListDataChanged();
	}
	
	public T get(int aIndex) {
		return data.get(aIndex);
	}

	public void setData(List<T> aData) {

		data = aData;
		fireListDataChanged();
	}

	public void clear() {
		data.clear();
		fireListDataChanged();
	}

	public boolean contains(T o) {
		return data.contains(o);
	}
}
