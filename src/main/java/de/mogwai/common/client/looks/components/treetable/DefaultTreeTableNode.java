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

import java.util.Vector;

public class DefaultTreeTableNode<T> {

	T value;

	private Vector<DefaultTreeTableNode> children = new Vector<DefaultTreeTableNode>();

	public DefaultTreeTableNode() {
		this(null);
	}

	public DefaultTreeTableNode(T aValue) {
		value = aValue;
	}

	public T getValue() {
		return value;
	}

	public DefaultTreeTableNode addChild(DefaultTreeTableNode aChild) {
		children.add(aChild);
		return aChild;
	}

	public int getChildCount() {
		return children.size();
	}

	public DefaultTreeTableNode getChild(int count) {
		return children.get(count);
	}
}