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

import java.awt.Dimension;

import javax.swing.JComboBox;

import de.mogwai.common.client.looks.components.renderer.DefaultComboboxRenderer;
import de.mogwai.common.client.looks.tools.DefaultModificationListener;

public class DefaultComboBox extends JComboBox {

    public DefaultComboBox() {

        initialize();
    }

    private void initialize() {

        setRenderer(DefaultComboboxRenderer.getInstance());
        addActionListener(new DefaultModificationListener(this));
    }

    @Override
    public Dimension getSize() {

        Dimension theSize = super.getSize();
        theSize.height = 21;
        return theSize;
    }

    @Override
    public Dimension getMinimumSize() {

        Dimension theSize = super.getMinimumSize();
        theSize.height = 21;
        return theSize;
    }

    @Override
    public Dimension getPreferredSize() {

        Dimension theSize = super.getPreferredSize();
        theSize.height = 21;
        return theSize;
    }

    @Override
    public Dimension getMaximumSize() {

        Dimension theSize = super.getMaximumSize();
        theSize.height = 21;
        return theSize;
    }
}
