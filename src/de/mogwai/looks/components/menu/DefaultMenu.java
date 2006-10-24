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
package de.mogwai.looks.components.menu;

import java.awt.Font;
import javax.swing.JMenu;
import de.mogwai.i18n.I18NAble;
import de.mogwai.i18n.ResourceHelper;
import de.mogwai.i18n.ResourceHelperLocator;
import de.mogwai.looks.UIInitializer;

public class DefaultMenu extends JMenu implements I18NAble {

    private String resourceID;

    public DefaultMenu(String aResourceID) {

        super(aResourceID);
        resourceID = aResourceID;
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

        return ResourceHelperLocator.findResourceHelperFor(this);
    }

    private void initialize() {

        UIInitializer.getInstance().initializeFontAndColors(this);
    }
}
