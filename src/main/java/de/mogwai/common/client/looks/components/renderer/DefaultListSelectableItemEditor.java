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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import de.mogwai.common.client.looks.UIInitializer;
import de.mogwai.common.client.looks.components.DefaultCheckBoxList;
import de.mogwai.common.client.looks.tools.DefaultSelectableItem;
import de.mogwai.common.client.looks.tools.ModificationTrackerHelper;
import de.mogwai.common.client.looks.tools.StringRendererHelper;

public class DefaultListSelectableItemEditor extends AbstractCellEditor implements TableCellEditor {

    private UIInitializer initializer = UIInitializer.getInstance();

    private JCheckBox checkBox = new JCheckBox();

    private DefaultSelectableItem currentItem;

    private DefaultCheckBoxList parent;

    public DefaultListSelectableItemEditor(DefaultCheckBoxList aParent) {

        parent = aParent;
        initialize();
    }

    private void initialize() {

        initializer.initializeComponent(checkBox);
        checkBox.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                currentItem.setSelected(checkBox.isSelected());
                ModificationTrackerHelper.setModified(parent, true);
                cancelCellEditing();
            }
        });
    }

    protected String objectToString(Object aObject) {

        return StringRendererHelper.objectToString(aObject);
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        currentItem = (DefaultSelectableItem) value;
        checkBox.setText(objectToString(currentItem.getValue()));
        checkBox.setSelected(currentItem.isSelected());
        if (isSelected) {
            checkBox.setBackground(initializer.getConfiguration().getDefaultListSelectionBackground());
            checkBox.setForeground(initializer.getConfiguration().getDefaultListSelectionForeground());
        } else {
            checkBox.setBackground(initializer.getConfiguration().getDefaultListNonSelectionBackground());
            checkBox.setForeground(initializer.getConfiguration().getDefaultListNonSelectionForeground());
        }
        return checkBox;
    }

    public Object getCellEditorValue() {

        return Boolean.valueOf(checkBox.isSelected());
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {

        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {

        return true;
    }
}
