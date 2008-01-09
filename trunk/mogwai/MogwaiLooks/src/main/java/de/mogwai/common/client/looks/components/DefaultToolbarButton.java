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
package de.mogwai.common.client.looks.components;

import javax.swing.Action;

public class DefaultToolbarButton extends DefaultButton {

	public DefaultToolbarButton(Action aAction) {
		super(aAction);
		initialize();
	}

	private void initialize() {
		setBorderPainted(false);
		setFocusable(false);
		/*setSize(new Dimension(32,32));
		setPreferredSize(new Dimension(32,32)); 
		setMinimumSize(new Dimension(32,32));*/
	}

	@Override
	public void setText(String aText) {
		super.setText("");
		setToolTipText(aText);
	}
}