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
package de.mogwai.common.client.looks.components.menu;

import java.awt.Font;

import javax.swing.Action;
import javax.swing.JMenuItem;

import de.mogwai.common.client.looks.UIInitializer;
import de.mogwai.common.client.looks.components.action.DefaultAction;
import de.mogwai.common.i18n.I18NAble;
import de.mogwai.common.i18n.ResourceHelper;
import de.mogwai.common.i18n.ResourceHelperLocator;

public class DefaultMenuItem extends JMenuItem implements I18NAble {

    private String resourceID;

    public DefaultMenuItem(String aResourceID) {

        super(aResourceID);
        resourceID = aResourceID;
        initialize();
    }

    public DefaultMenuItem(Action aAction) {

        super(aAction);
        initialize();
    }

    @Override
    public void setFont(Font aFont) {

        super.setFont(aFont.deriveFont(Font.PLAIN));
    }

    public String getResourceBundleID() {

        return resourceID;
    }

    public ResourceHelper getResourceHelper() {

        Action theAction = getAction();
        if (theAction instanceof DefaultAction) {
            return ((DefaultAction) theAction).getResourceHelper();
        }
        return ResourceHelperLocator.findResourceHelperFor(this);
    }

    private void initialize() {

        UIInitializer.getInstance().initializeFontAndColors(this);
    }
}