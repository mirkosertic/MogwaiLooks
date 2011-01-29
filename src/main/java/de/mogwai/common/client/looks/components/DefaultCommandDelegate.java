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

import java.awt.*;
import java.lang.reflect.Method;

public class DefaultCommandDelegate {

    private Component parent;

    public DefaultCommandDelegate(Component aParent) {

        parent = aParent;
    }

    public void execute(String aCommand) {

        execute(aCommand, parent);
    }

    private boolean execute(String aCommand, Component aObject) {

        Method theMethod = null;
        try {
            theMethod = aObject.getClass().getMethod(aCommand, new Class[]{});
        } catch (Exception e) {
            // Do nothing
        }
        if (theMethod != null) {
            try {
                theMethod.invoke(aObject);
                return true;
            } catch (Exception e) {
                return true;
            }
        } else {
            aObject = aObject.getParent();
            if (aObject != null) {
                return execute(aCommand, aObject);
            } else {
                return false;
            }
        }
    }
}