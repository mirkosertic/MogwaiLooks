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

import java.util.Vector;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class DefaultToolbar extends JToolBar {

    public DefaultToolbar() {

        initialize();
    }

    private void initialize() {
        setFloatable(false);
    }

    @Override
    public JButton add(Action aAction) {
        DefaultToolbarButton theButton = new DefaultToolbarButton(aAction);
        add(theButton);
        return theButton;
    }

    public void addActions(Vector<Action> registeredActions) {
        for (Action theAction : registeredActions) {
            DefaultToolbarButton theButton = new DefaultToolbarButton(theAction);
            add(theButton);
        }
    }
}
