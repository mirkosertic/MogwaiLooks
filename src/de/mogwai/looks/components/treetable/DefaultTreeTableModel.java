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
package de.mogwai.looks.components.treetable;

import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public abstract class DefaultTreeTableModel implements TreeModel {

	protected DefaultTreeTableNode root;

	protected EventListenerList listenerList = new EventListenerList();

	protected DefaultTreeTableDescriptor descriptor;

	public DefaultTreeTableModel(DefaultTreeTableNode root,
			DefaultTreeTableDescriptor aDescriptor) {

		this.root = root;
		descriptor = aDescriptor;
	}

	public DefaultTreeTableDescriptor getDescriptor() {
		return descriptor;
	}

	public DefaultTreeTableNode getRoot() {

		return root;
	}

	public void setRoot(DefaultTreeTableNode aRoot) {
		root = aRoot;
		fireTreeStructureChanged(this);
	}

	public boolean isLeaf(Object node) {

		return getChildCount(node) == 0;
	}

	public void valueForPathChanged(TreePath path, Object newValue) {

	}

	public int getIndexOfChild(Object parent, Object child) {

		for (int i = 0; i < getChildCount(parent); i++) {
			if (getChild(parent, i).equals(child)) {
				return i;
			}
		}
		return -1;
	}

	public void addTreeModelListener(TreeModelListener l) {

		listenerList.add(TreeModelListener.class, l);
	}

	public void removeTreeModelListener(TreeModelListener l) {

		listenerList.remove(TreeModelListener.class, l);
	}

	protected void fireTreeNodesChanged(Object source, Object[] path,
			int[] childIndices, Object[] children) {

		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				if (e == null)
					e = new TreeModelEvent(source, path, childIndices, children);
				((TreeModelListener) listeners[i + 1]).treeNodesChanged(e);
			}
		}
	}

	protected void fireTreeNodesInserted(Object source, Object[] path,
			int[] childIndices, Object[] children) {

		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				if (e == null)
					e = new TreeModelEvent(source, path, childIndices, children);
				((TreeModelListener) listeners[i + 1]).treeNodesInserted(e);
			}
		}
	}

	protected void fireTreeNodesRemoved(Object source, Object[] path,
			int[] childIndices, Object[] children) {

		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				if (e == null)
					e = new TreeModelEvent(source, path, childIndices, children);
				((TreeModelListener) listeners[i + 1]).treeNodesRemoved(e);
			}
		}
	}

	public void fireTreeStructureChanged(Object source) {

		Object[] listeners = listenerList.getListenerList();
		TreeModelEvent e = null;
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == TreeModelListener.class) {
				if (e == null)
					e = new TreeModelEvent(source, (TreePath) null, null, null);
				((TreeModelListener) listeners[i + 1]).treeStructureChanged(e);
			}
		}
	}

	public boolean isCellEditable(Object node, int column) {

		return getColumnClass(column) == DefaultTreeTableModel.class;
	}

	public void setValueAt(Object aValue, Object node, int column) {
	}

	public Object getChild(Object parent, int index) {
		return ((DefaultTreeTableNode) parent).getChild(index);
	}

	public int getChildCount(Object parent) {
		return ((DefaultTreeTableNode) parent).getChildCount();
	}

	public int getColumnCount() {
		return descriptor.getColumnCount();
	}

	public String getColumnName(int column) {
		return descriptor.getColumnName(column);
	}

	public Class getColumnClass(int column) {
		return descriptor.getColumnClass(column);
	}

	public abstract Object getValueAt(DefaultTreeTableNode node, int column);
}
