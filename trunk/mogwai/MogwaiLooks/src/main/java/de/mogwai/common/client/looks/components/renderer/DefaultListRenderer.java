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

import javax.swing.JList;
import javax.swing.ListCellRenderer;

import de.mogwai.common.client.looks.UIInitializer;

public final class DefaultListRenderer extends DefaultRenderer implements ListCellRenderer {

    private static DefaultListRenderer me = new DefaultListRenderer();

    private UIInitializer initializer;

    public static DefaultListRenderer getInstance() {

        return me;
    }

    private DefaultListRenderer() {

        initializer = UIInitializer.getInstance();
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {

        setText(objectToString(value));
        if (isSelected) {
            setBackground(initializer.getDefaultListSelectionBackground());
            setForeground(initializer.getDefaultListSelectionForeground());
        } else {
            setBackground(initializer.getDefaultListNonSelectionBackground());
            setForeground(initializer.getDefaultListNonSelectionForeground());
        }
        return this;
    }
}