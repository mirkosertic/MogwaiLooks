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

import javax.swing.JFrame;
import de.mogwai.i18n.I18NAble;
import de.mogwai.i18n.ResourceHelper;
import de.mogwai.i18n.ResourceHelperProvider;
import de.mogwai.looks.components.menu.DefaultMenuBar;
import de.mogwai.looks.tools.WindowHelper;

public class DefaultFrame extends JFrame implements ResourceHelperProvider,
        I18NAble, ToolbarProvider {

    protected DefaultMenuBar menuBar = new DefaultMenuBar();

    protected DefaultFrameContent frameContent = new DefaultFrameContent();

    protected DefaultToolbar toolbar = new DefaultToolbar();

    private String resourceID;

    public DefaultFrame(String aResourceID) {

        resourceID = aResourceID;
        initialize();
    }

    public DefaultFrame() {

        this(null);
    }

    private void initialize() {

        setJMenuBar(menuBar);
        setContentPane(frameContent);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public DefaultFrameContent getDefaultFrameContent() {
        return frameContent;
    }

    public ResourceHelper getResourceHelper() {

        return null;
    }

    public String getResourceBundleID() {

        return resourceID;
    }

    public void setText(String aText) {

        setTitle(aText);
    }

    public DefaultToolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void setVisible(boolean bVisible) {
        if (bVisible) {
            new WindowHelper(this).center();
        }
        super.setVisible(bVisible);
    }
}
