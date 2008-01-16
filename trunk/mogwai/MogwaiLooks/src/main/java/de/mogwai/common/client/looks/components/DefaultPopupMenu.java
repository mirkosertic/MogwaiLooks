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
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import de.mogwai.common.client.looks.UIInitializer;
import de.mogwai.common.i18n.ResourceHelper;
import de.mogwai.common.i18n.ResourceHelperProvider;

public class DefaultPopupMenu extends JPopupMenu implements ResourceHelperProvider {

    private ResourceHelper resourceHelper;

    public DefaultPopupMenu() {
        this(null);
    }

    public DefaultPopupMenu(ResourceHelper aHelper) {
        initialize();
        resourceHelper = aHelper;
    }

    private void initialize() {
        UIInitializer.getInstance().initializeFontAndColors(this);
    }

    @Override
    public JMenuItem add(Action aAction) {
        JMenuItem theItem = super.add(aAction);
        UIInitializer.getInstance().initializeFontAndColors(theItem);
        return theItem;
    }

    public ResourceHelper getResourceHelper() {
        return resourceHelper;
    }
}
