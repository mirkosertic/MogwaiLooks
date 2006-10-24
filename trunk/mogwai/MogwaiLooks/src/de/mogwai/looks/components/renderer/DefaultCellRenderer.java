/**
 * Mogwai Looks.
 * Copyright (C) 2002 The Mogwai Project.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package de.mogwai.looks.components.renderer;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.TreeCellRenderer;
import de.mogwai.looks.UIInitializer;

public class DefaultCellRenderer extends DefaultRenderer implements
        TableCellRenderer, TreeCellRenderer {

    private static DefaultCellRenderer me = new DefaultCellRenderer();

    private UIInitializer initializer;

    public static DefaultCellRenderer getInstance() {

        return me;
    }

    private DefaultCellRenderer() {

        initializer = UIInitializer.getInstance();
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        setText(objectToString(value));
        setIcon(objectToIcon(value));
        if (isSelected) {
            setBackground(initializer.getDefaultListSelectionBackground());
            setForeground(initializer.getDefaultListSelectionForeground());
        } else {
            setBackground(initializer.getDefaultListNonSelectionBackground());
            setForeground(initializer.getDefaultListNonSelectionForeground());
        }
        return this;
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value,
            boolean selected, boolean expanded, boolean leaf, int row,
            boolean hasFocus) {

        setText(objectToString(value));
        setIcon(objectToIcon(value));

        setText(objectToString(value));
        if (selected) {
            setBackground(initializer.getDefaultListSelectionBackground());
            setForeground(initializer.getDefaultListSelectionForeground());
        } else {
            setBackground(initializer.getDefaultListNonSelectionBackground());
            setForeground(initializer.getDefaultListNonSelectionForeground());
        }
        return this;
    }
}
