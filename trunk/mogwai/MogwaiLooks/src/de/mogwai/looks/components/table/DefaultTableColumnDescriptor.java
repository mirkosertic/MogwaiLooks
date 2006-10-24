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
package de.mogwai.looks.components.table;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import de.mogwai.looks.components.renderer.DefaultCellRenderer;

public class DefaultTableColumnDescriptor {

    private String displayName;

    private Class clazz;

    private String propertyName;

    private int size = -1;

    private TableCellRenderer renderer = DefaultCellRenderer.getInstance();

    private TableCellEditor editor;

    private boolean editable;

    public DefaultTableColumnDescriptor(String aDisplayName, Class aClazz,
            String aPropertyName, int aSize, boolean bEditable) {
        displayName = aDisplayName;
        clazz = aClazz;
        propertyName = aPropertyName;
        size = aSize;
        editable = bEditable;

        if ((bEditable) && clazz.equals(String.class)) {
            editor = new DefaultTextFieldCellEditor();
        }
    }

    public Class getClazz() {

        return clazz;
    }

    public String getDisplayName() {

        return displayName;
    }

    public boolean isEditable() {

        return editable;
    }

    public String getPropertyName() {

        return propertyName;
    }

    public TableCellRenderer getRenderer() {

        return renderer;
    }

    public int getSize() {

        return size;
    }

    public TableCellEditor getEditor() {

        return editor;
    }

    public void setEditor(TableCellEditor editor) {

        this.editor = editor;
    }

}