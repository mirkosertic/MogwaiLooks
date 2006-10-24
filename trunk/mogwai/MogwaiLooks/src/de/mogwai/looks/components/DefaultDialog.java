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
import java.awt.event.ActionEvent;
import javax.swing.JDialog;
import de.mogwai.i18n.I18NAble;
import de.mogwai.i18n.ResourceHelper;
import de.mogwai.i18n.ResourceHelperProvider;
import de.mogwai.looks.UIInitializer;
import de.mogwai.looks.components.action.ActionEventProcessor;
import de.mogwai.looks.components.action.ActionEventProcessorHelper;
import de.mogwai.looks.tools.ModificationTracker;
import de.mogwai.looks.tools.WindowHelper;

public class DefaultDialog extends JDialog implements ResourceHelperProvider,
        I18NAble, ModificationTracker, ResultProvider, ActionEventProcessor {

    private String resourceID;

    private ResourceHelper resource;

    private DefaultFrameContent content = new DefaultFrameContent();

    private boolean modified;

    private Object result;

    public DefaultDialog(Component parent, String aBundleName,
            String aResourceID) {

        super(WindowHelper.findRootFrame(parent), aResourceID, true);
        resourceID = aResourceID;
        resource = ResourceHelper.getResourceHelper(aBundleName);
        initialize();
        UIInitializer.getInstance().initialize(this);
    }

    public DefaultDialog(Component parent, ResourceHelper aHelper,
            String aResourceID) {

        super(WindowHelper.findRootFrame(parent), aResourceID, true);
        resourceID = aResourceID;
        resource = aHelper;
        initialize();
        UIInitializer.getInstance().initialize(this);
    }

    public DefaultDialog(Component parent, String aBundleName,
            String aResourceID, DefaultFrameContent aContent) {

        this(parent, aBundleName, aResourceID);
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
        setContentPane(content);
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
    }

    public boolean isModified() {
        return modified;
    }

    @Override
    public void setVisible(boolean bStatus) {
        if (bStatus) {
            new WindowHelper(this).center();
        }
        super.setVisible(bStatus);
    }

    public void setResult(Object aResult) {
        result = aResult;
    }

    public Object getModalResult() {
        pack();
        setResizable(false);
        setVisible(true);
        return result;
    }

    public void processActionEvent(ActionEvent e) {
        ActionEventProcessorHelper.invoke(this, e);
    }
}
