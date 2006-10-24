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
package de.mogwai.looks.components.treetable;

import javax.swing.table.TableCellRenderer;
import de.mogwai.looks.components.renderer.DefaultCellRenderer;

public class DefaultTreeTableColumnDescriptor {

    private String displayName;

    private Class clazz;

    private int size = -1;

    private TableCellRenderer renderer = DefaultCellRenderer.getInstance();

    public DefaultTreeTableColumnDescriptor(String aDisplayName, Class aClazz,
            int aSize) {
        displayName = aDisplayName;
        clazz = aClazz;
        size = aSize;
    }

    public Class getClazz() {

        return clazz;
    }

    public String getDisplayName() {

        return displayName;
    }

    public TableCellRenderer getRenderer() {

        return renderer;
    }

    public int getSize() {

        return size;
    }
}