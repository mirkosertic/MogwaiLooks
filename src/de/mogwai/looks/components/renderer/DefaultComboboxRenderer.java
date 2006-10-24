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
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import de.mogwai.looks.UIInitializer;

public class DefaultComboboxRenderer extends DefaultRenderer implements
        ListCellRenderer {

    private static DefaultComboboxRenderer me = new DefaultComboboxRenderer();

    private UIInitializer initializer;

    public static DefaultComboboxRenderer getInstance() {

        return me;
    }

    private DefaultComboboxRenderer() {

        initializer = UIInitializer.getInstance();
    }

    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean cellHasFocus) {

        setText(objectToString(value));
        if (isSelected) {
            setBackground(initializer.getDefaultListSelectionBackground());
            setForeground(initializer.getDefaultListSelectionForeground());
        } else {
            setBackground(initializer.getDefaultBackgroundColor());
            setForeground(initializer.getDefaultListNonSelectionForeground());
        }
        return this;
    }
}
