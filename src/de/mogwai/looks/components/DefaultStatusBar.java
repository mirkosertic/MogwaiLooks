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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import de.mogwai.i18n.ResourceHelperLocator;
import de.mogwai.looks.UIInitializer;

public class DefaultStatusBar extends JLabel implements StatusBar {

    public DefaultStatusBar() {

        setBorder(BorderFactory.createLoweredBevelBorder());
        setPreferredSize(new Dimension(100, 21));
    }

    @Override
    public void setText(String aText) {

        super.setText(" " + aText);
    }

    @Override
    public void setFont(Font aFont) {

        super.setFont(aFont.deriveFont(Font.BOLD));
    }

    public void clear() {

        setText("");
    }

    public void showErrors(Vector errorList) {

        if (errorList.size() < 1) {
            clear();
        } else {
            showError(errorList.get(0).toString());
        }
    }

    public void showError(String aMessage) {

        setForeground(UIInitializer.getInstance().getDefaultErrorColor());
        setText(aMessage);
    }

    public void showErrorByID(String aMessageID) {

        setForeground(UIInitializer.getInstance().getDefaultErrorColor());
        setText(ResourceHelperLocator.findResourceHelperFor(this).getText(
                aMessageID));
    }

    public void showInformation(String aMessage) {

        setForeground(Color.black);
        setText(aMessage);
    }

    public void showInformationByID(String aMessageID) {

        setForeground(Color.black);
        setText(ResourceHelperLocator.findResourceHelperFor(this).getText(
                aMessageID));
    }
}
