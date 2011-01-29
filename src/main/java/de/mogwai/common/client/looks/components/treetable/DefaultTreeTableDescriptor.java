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

import de.mogwai.common.i18n.ResourceHelper;
import de.mogwai.common.i18n.ResourceHelperProvider;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.util.Vector;

public class DefaultTreeTableDescriptor {

    private Vector<DefaultTreeTableColumnDescriptor> column = new Vector<DefaultTreeTableColumnDescriptor>();

    protected ResourceHelper resourceHelper;

    protected DefaultTreeTableDescriptor(ResourceHelper aResourceHelper) {

        resourceHelper = aResourceHelper;
    }

    protected DefaultTreeTableDescriptor(ResourceHelperProvider aProvider) {

        this(aProvider.getResourceHelper());
    }

    protected DefaultTreeTableColumnDescriptor addColumn(String displayName, Class clazz) {
        return addColumn(displayName, clazz, -1);
    }

    protected DefaultTreeTableColumnDescriptor addColumn(String aResourceKey, Class clazz, int size) {

        if (column.size() == 0) {
            clazz = DefaultTreeTableModel.class;
        }

        DefaultTreeTableColumnDescriptor theDesc = new DefaultTreeTableColumnDescriptor(resourceHelper
                .getText(aResourceKey), clazz, size);

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

        if (columnIndex == 0) {
            return DefaultTreeTableModel.class;
        }
        return column.get(columnIndex).getClass();
    }

    public boolean isColumnEditable(int columnIndex) {
        return false;
    }

    public void configureTable(DefaultTreeTable aTable) {

        aTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        aTable.setRowSelectionAllowed(true);
        aTable.setColumnSelectionAllowed(false);
        aTable.setRowSelectionAllowed(true);
        aTable.getTableHeader().setReorderingAllowed(false);
        for (DefaultTreeTableColumnDescriptor theDesc : column) {
            int index = column.indexOf(theDesc);
            TableColumn theColumn = aTable.getColumnModel().getColumn(index);
            if (theDesc.getSize() > 0) {
                theColumn.setMinWidth(theDesc.getSize());
                theColumn.setMaxWidth(theDesc.getSize());
                theColumn.setWidth(theDesc.getSize());
            }
            if (index > 0) {
                theColumn.setCellRenderer(theDesc.getRenderer());
            }
        }
    }
}
