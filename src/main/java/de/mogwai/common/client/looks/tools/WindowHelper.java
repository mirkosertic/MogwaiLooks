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
package de.mogwai.common.client.looks.tools;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.IllegalComponentStateException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JFrame;

public class WindowHelper {

    private static int screenBottomOffset = 23;

    private Window window;

    public static JFrame findRootFrame(Component aComponent) {

        if (aComponent == null) {
            return null;
        }

        if (aComponent instanceof JFrame) {
            return (JFrame) aComponent;
        }

        if (aComponent.getParent() != null) {
            return findRootFrame(aComponent.getParent());
        }

        return null;
    }

    public WindowHelper(Window window) {

        this.window = window;
    }

    public void center() {

        if (!isDoubleScreen()) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension windowSize = window.getSize();
            window.setLocation(calculateLocationWidth(windowSize, screenSize.width), calculateLocationHeigth(
                    windowSize, screenSize));
        } else {
            centerOnLeftScreen();
        }
    }

    private int calculateLocationHeigth(Dimension windowSize, Dimension screenSize) {

        if (windowSize.height > screenSize.height) {
            windowSize.height = screenSize.height;
        }
        int height = (screenSize.height - windowSize.height) / 2;
        if (height < screenBottomOffset) {
            height = 0;
        }
        return height;
    }

    private int calculateLocationWidth(Dimension windowSize, int screenSizeWidth) {

        if (windowSize.width > screenSizeWidth) {
            windowSize.width = screenSizeWidth;
        }
        return (screenSizeWidth - windowSize.width) / 2;
    }

    public void center(Component component) {

        try {
            Dimension parentSize = component.getSize();
            Dimension windowSize = window.getSize();
            int relativeX = (parentSize.width - windowSize.width) / 2;
            int relativeY = (parentSize.height - windowSize.height) / 2;
            Point topLeft = component.getLocationOnScreen();
            int absoluteX = (int) topLeft.getX() + relativeX;
            if (absoluteX < 0) {
                absoluteX = 0;
            }
            int absoluteY = (int) topLeft.getY() + relativeY;
            if (absoluteY < 0) {
                absoluteY = 0;
            }
            window.setLocation(absoluteX, absoluteY);
        } catch (IllegalComponentStateException e) {
            center();
        }
    }

    public void centerOnLeftScreen() {

        if (!isDoubleScreen()) {
            center();
            return;
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = window.getSize();
        int halfWidth = screenSize.width / 2;
        window.setLocation(calculateLocationWidth(windowSize, halfWidth), calculateLocationHeigth(windowSize,
                screenSize));
    }

    public void centerOnRightScreen() {

        if (!isDoubleScreen()) {
            center();
            return;
        }
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = window.getSize();
        int halfWidth = screenSize.width / 2;
        window.setLocation(calculateLocationWidth(windowSize, halfWidth) + halfWidth, calculateLocationHeigth(
                windowSize, screenSize));
    }

    public boolean isDoubleScreen() {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double ratio = (double) screenSize.width / (double) screenSize.height;
        boolean doubleScreen = ratio > 2.0;
        return doubleScreen;
    }
}