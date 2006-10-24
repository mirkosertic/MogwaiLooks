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
package de.mogwai.looks.components;

import java.awt.Dimension;
import javax.swing.JComboBox;
import de.mogwai.looks.components.renderer.DefaultComboboxRenderer;
import de.mogwai.looks.tools.DefaultModificationListener;

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
