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
package de.mogwai.common.client.looks.components.treetable;

import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.TreePath;

public class DefaultTreeTableModelAdapter extends AbstractTableModel {

    private JTree tree;

    private DefaultTreeTableModel treeTableModel;

    public DefaultTreeTableModelAdapter(DefaultTreeTableModel treeTableModel, JTree tree) {

        this.tree = tree;
        this.treeTableModel = treeTableModel;
        tree.addTreeExpansionListener(new TreeExpansionListener() {

            public void treeExpanded(TreeExpansionEvent event) {

                fireTableDataChanged();
            }

            public void treeCollapsed(TreeExpansionEvent event) {

                fireTableDataChanged();
            }
        });
    }

    public int getColumnCount() {

        return treeTableModel.getColumnCount();
    }

    @Override
    public String getColumnName(int column) {

        return treeTableModel.getColumnName(column);
    }

    @Override
    public Class getColumnClass(int column) {

        return treeTableModel.getColumnClass(column);
    }

    public int getRowCount() {

        return tree.getRowCount();
    }

    protected DefaultTreeTableNode nodeForRow(int row) {

        TreePath treePath = tree.getPathForRow(row);
        return (DefaultTreeTableNode) treePath.getLastPathComponent();
    }

    public Object getValueAt(int row, int column) {

        return treeTableModel.getValueAt(nodeForRow(row), column);
    }

    @Override
    public boolean isCellEditable(int row, int column) {

        return treeTableModel.isCellEditable(nodeForRow(row), column);
    }

    @Override
    public void setValueAt(Object value, int row, int column) {

        treeTableModel.setValueAt(value, nodeForRow(row), column);
    }
}