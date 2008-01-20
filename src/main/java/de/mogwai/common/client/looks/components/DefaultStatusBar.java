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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import de.mogwai.common.client.looks.UIInitializer;
import de.mogwai.common.i18n.ResourceHelperLocator;

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

        setForeground(UIInitializer.getInstance().getConfiguration().getDefaultErrorColor());
        setText(aMessage);
    }

    public void showErrorByID(String aMessageID) {

        setForeground(UIInitializer.getInstance().getConfiguration().getDefaultErrorColor());
        setText(ResourceHelperLocator.findResourceHelperFor(this).getText(aMessageID));
    }

    public void showInformation(String aMessage) {

        setForeground(Color.black);
        setText(aMessage);
    }

    public void showInformationByID(String aMessageID) {

        setForeground(Color.black);
        setText(ResourceHelperLocator.findResourceHelperFor(this).getText(aMessageID));
    }
}
