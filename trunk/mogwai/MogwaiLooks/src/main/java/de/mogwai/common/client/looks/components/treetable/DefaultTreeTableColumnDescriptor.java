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

import javax.swing.table.TableCellRenderer;

import de.mogwai.common.client.looks.components.renderer.DefaultCellRenderer;

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