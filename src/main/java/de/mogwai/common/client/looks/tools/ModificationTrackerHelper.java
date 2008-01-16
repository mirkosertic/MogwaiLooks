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

public class ModificationTrackerHelper {

    public static void setModified(Component aComponent, boolean modified) {

        if (aComponent instanceof ModificationTracker) {
            ModificationTracker theTracker = (ModificationTracker) aComponent;
            if (theTracker.isModified() != modified) {
                theTracker.setModified(modified);
            }
        }
        if (aComponent.getParent() != null) {
            setModified(aComponent.getParent(), modified);
        }

    }
}
