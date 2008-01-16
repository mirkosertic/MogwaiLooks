package de.mogwai.common.client.looks.components.renderer;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class DefaultCheckboxTableCellRenderer implements TableCellRenderer {

    private JCheckBox checkbox = new JCheckBox();

    public Component getTableCellRendererComponent(JTable aTable, Object aValue, boolean isSelected, boolean hasFocus,
            int aRow, int aColumn) {
        if (aValue != null) {
            checkbox.setSelected((Boolean) aValue);
        } else {
            checkbox.setSelected(false);
        }
        return checkbox;
    }

}
