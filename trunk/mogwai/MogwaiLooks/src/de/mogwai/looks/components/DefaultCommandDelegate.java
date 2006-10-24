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

import java.awt.Component;
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
            theMethod = aObject.getClass().getMethod(aCommand, new Class[] {});
        } catch (Exception e) {
        }
        if (theMethod != null) {
            try {
                theMethod.invoke(aObject, new Object[] {});
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