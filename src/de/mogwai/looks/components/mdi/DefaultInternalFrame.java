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
package de.mogwai.looks.components.mdi;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import de.mogwai.i18n.I18NAble;
import de.mogwai.i18n.ResourceHelper;
import de.mogwai.i18n.ResourceHelperProvider;
import de.mogwai.looks.UIInitializer;
import de.mogwai.looks.components.DefaultFrameContent;
import de.mogwai.looks.tools.ModificationTracker;

public class DefaultInternalFrame extends JInternalFrame implements
        ResourceHelperProvider, I18NAble, ModificationTracker {

    private String resourceID;

    private ResourceHelper resource;

    private DefaultFrameContent content = new DefaultFrameContent();

    private boolean modified;

    public DefaultInternalFrame(String aBundleName, String aResourceID) {

        super(aResourceID);
        resourceID = aResourceID;
        resource = ResourceHelper.getResourceHelper(aBundleName);
        initialize();
        UIInitializer.getInstance().initialize(this);
    }

    public DefaultInternalFrame(String aBundleName, String aResourceID,
            DefaultFrameContent aContent) {

        this(aBundleName, aResourceID);
        content = aContent;
        setContentPane(content);
        UIInitializer.getInstance().initialize(this);
        content.initializeBindingInfo();
        pack();
    }

    public DefaultFrameContent getDefaultFrameContent() {

        return content;
    }

    private void initialize() {

        setVisible(true);
        setClosable(true);
        setContentPane(content);
        setBorder(BorderFactory.createLineBorder(UIInitializer.getInstance()
                .getDefaultInternalFrameBorderColor(), 1));

    }

    public void activate() {

        try {
            setSelected(true);
        } catch (Exception e) {
        }
    }

    public ResourceHelper getResourceHelper() {

        return resource;
    }

    public String getResourceBundleID() {

        return resourceID;
    }

    public void setText(String aText) {

        setTitle(aText);
    }

    public void setModified(boolean bModified) {
        modified = bModified;
        setClosable(!bModified);
    }

    public boolean isModified() {
        return modified;
    }

    @Override
    public void setVisible(boolean bStatus) {
        super.setVisible(bStatus);
        if (!bStatus) {
            InternalFrameEvent theEvent = new InternalFrameEvent(this,
                    InternalFrameEvent.INTERNAL_FRAME_CLOSING);
            for (InternalFrameListener theListener : getListeners(InternalFrameListener.class)) {
                theListener.internalFrameClosing(theEvent);
            }
        }
    }
}
