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
package de.mogwai.looks.tools;

import java.util.HashMap;
import javax.swing.ImageIcon;

public class ImageIconCache {

    private static HashMap<String, ImageIcon> icons = new HashMap<String, ImageIcon>();

    public static ImageIcon getImageIcon(String aIconName) {
        if (icons.containsKey(aIconName))
            return icons.get(aIconName);

        ImageIcon aIcon = new ImageIcon(ImageIconCache.class
                .getResource(aIconName));
        icons.put(aIconName, aIcon);

        return aIcon;
    }
}
