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

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JDialog;

import de.mogwai.common.client.looks.UIInitializer;
import de.mogwai.common.client.looks.components.action.ActionEventProcessor;
import de.mogwai.common.client.looks.components.action.ActionEventProcessorHelper;
import de.mogwai.common.client.looks.tools.ModificationTracker;
import de.mogwai.common.client.looks.tools.WindowHelper;
import de.mogwai.common.i18n.I18NAble;
import de.mogwai.common.i18n.ResourceHelper;
import de.mogwai.common.i18n.ResourceHelperProvider;

public class DefaultDialog extends JDialog implements ResourceHelperProvider, I18NAble, ModificationTracker,
        ResultProvider, ActionEventProcessor {

    private String resourceID;

    private ResourceHelper resource;

    private DefaultFrameContent content = new DefaultFrameContent();

    private boolean modified;

    private Object result;

    public DefaultDialog(Component parent, String aBundleName, String aResourceID) {

        super(WindowHelper.findRootFrame(parent), aResourceID, true);
        resourceID = aResourceID;
        resource = ResourceHelper.getResourceHelper(aBundleName);
        initialize();
        UIInitializer.getInstance().initialize(this);
    }

    public DefaultDialog(Component parent, ResourceHelper aHelper, String aResourceID) {

        super(WindowHelper.findRootFrame(parent), aResourceID, true);
        resourceID = aResourceID;
        resource = aHelper;
        initialize();
        UIInitializer.getInstance().initialize(this);
    }

    public DefaultDialog(Component parent, String aBundleName, String aResourceID, DefaultFrameContent aContent) {

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
