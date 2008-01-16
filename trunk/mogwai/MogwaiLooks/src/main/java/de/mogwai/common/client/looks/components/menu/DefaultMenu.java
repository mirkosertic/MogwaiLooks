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
import javax.swing.JMenu;

import de.mogwai.common.client.looks.UIInitializer;
import de.mogwai.common.client.looks.components.action.DefaultAction;
import de.mogwai.common.i18n.I18NAble;
import de.mogwai.common.i18n.ResourceHelper;
import de.mogwai.common.i18n.ResourceHelperLocator;
import de.mogwai.common.i18n.ResourceHelperProvider;

public class DefaultMenu extends JMenu implements I18NAble {

    private String resourceID;

    private ResourceHelper resourceHelper;

    public DefaultMenu(DefaultAction aAction) {
        super(aAction);

        resourceHelper = aAction.getResourceHelper();

        initialize();
    }

    public DefaultMenu(ResourceHelper aHelper, Action aAction) {
        super(aAction);

        resourceHelper = aHelper;

        initialize();
    }

    public DefaultMenu(ResourceHelperProvider aProvider, String aResourceID) {

        super(aResourceID);

        resourceID = aResourceID;
        resourceHelper = aProvider.getResourceHelper();

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

        if (resourceHelper != null) {
            return resourceHelper;
        }

        return ResourceHelperLocator.findResourceHelperFor(this);
    }

    private void initialize() {

        setOpaque(true);
        UIInitializer.getInstance().initializeFontAndColors(this);
    }
}
