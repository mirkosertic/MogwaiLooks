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

import javax.swing.JLabel;
import de.mogwai.i18n.I18NAble;
import de.mogwai.i18n.ResourceHelper;
import de.mogwai.i18n.ResourceHelperLocator;

public class DefaultLabel extends JLabel implements I18NAble {

    private String resourceID;

    private boolean colon = true;

    public DefaultLabel() {

        initialize();
    }

    public DefaultLabel(String aResourceKey) {

        super(aResourceKey);
        resourceID = aResourceKey;
        initialize();
    }

    private void initialize() {

        setVerticalAlignment(TOP);
    }

    public String getResourceBundleID() {

        return resourceID;
    }

    public ResourceHelper getResourceHelper() {

        return ResourceHelperLocator.findResourceHelperFor(this);
    }

    @Override
    public void setText(String aText) {

        if (colon)
            aText += ":";
        super.setText(aText);
    }

    public boolean hasColon() {

        return colon;
    }

    public void setColon(boolean colon) {

        this.colon = colon;
    }
}
