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

import javax.swing.JTabbedPane;

import de.mogwai.common.i18n.I18NAble;
import de.mogwai.common.i18n.ResourceHelper;
import de.mogwai.common.i18n.ResourceHelperLocator;

public class DefaultTabbedPaneTab extends DefaultPanel implements I18NAble {

    private JTabbedPane parent;

    private String resourceId;

    public DefaultTabbedPaneTab(JTabbedPane aParent, String aResourceID) {
        parent = aParent;
        resourceId = aResourceID;
    }

    public String getResourceBundleID() {
        return resourceId;
    }

    public ResourceHelper getResourceHelper() {
        return ResourceHelperLocator.findResourceHelperFor(this);
    }

    public void setText(String aText) {
        setTitle(aText);
    }

    public String getTitle() {

        return parent.getTitleAt(parent.indexOfComponent(this));
    }

    public void setTitle(String title) {
        parent.setTitleAt(parent.indexOfComponent(this), title);
    }

}
