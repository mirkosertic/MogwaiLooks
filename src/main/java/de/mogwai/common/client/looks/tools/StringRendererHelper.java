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

import java.util.HashMap;

import javax.swing.Icon;

import de.mogwai.common.client.looks.components.treetable.DefaultTreeTableNode;

public class StringRendererHelper {

    static {

        translators = new HashMap<Class, StringTranslator>();

        StringRendererHelper.registerTranslator(DefaultTreeTableNode.class,
                new StringTranslator<DefaultTreeTableNode>() {

                    public String createStringFromObject(DefaultTreeTableNode aValue) {
                        if (aValue == null) {
                            return "";
                        }
                        if (aValue.getValue() == null) {
                            return "Root";
                        }

                        return StringRendererHelper.objectToString(aValue.getValue());
                    }

                    public Icon getIcon(DefaultTreeTableNode aObject) {
                        return null;
                    }

                });

    }

    private static HashMap<Class, StringTranslator> translators;

    public static void registerTranslator(Class aClass, StringTranslator aTranslator) {
        translators.put(aClass, aTranslator);
    }

    public static String objectToString(Object aObject) {

        if (aObject == null) {
            return "";
        }

        StringTranslator theTranslator = translators.get(aObject.getClass());
        if (theTranslator != null) {
            return theTranslator.createStringFromObject(aObject);
        }
        return aObject.toString();
    }

    public static Icon objectToIcon(Object aObject) {

        if (aObject != null) {
            StringTranslator theTranslator = translators.get(aObject.getClass());
            if (theTranslator != null) {
                return theTranslator.getIcon(aObject);
            }
        }
        return null;
    }
}
