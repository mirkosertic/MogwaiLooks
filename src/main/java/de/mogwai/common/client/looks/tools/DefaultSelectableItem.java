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
package de.mogwai.common.client.looks.tools;

public class DefaultSelectableItem<Typ> {

	private boolean selected;

	private Typ value;

	public DefaultSelectableItem(Typ aValue) {

		this(aValue, false);
	}

	public DefaultSelectableItem(Typ aValue, boolean aSelected) {

		value = aValue;
		selected = aSelected;
	}

	public Typ getValue() {

		return value;
	}

	public boolean isSelected() {

		return selected;
	}

	public void setSelected(boolean selected) {

		this.selected = selected;
	}

	public void setValue(Typ value) {

		this.value = value;
	}

	@Override
	public String toString() {

		return value.toString();
	}
}
