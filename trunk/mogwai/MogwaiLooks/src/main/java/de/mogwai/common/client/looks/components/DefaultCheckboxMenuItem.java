package de.mogwai.common.client.looks.components;

import java.awt.Font;

import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;

import de.mogwai.common.client.looks.UIInitializer;
import de.mogwai.common.client.looks.components.action.DefaultAction;
import de.mogwai.common.i18n.I18NAble;
import de.mogwai.common.i18n.ResourceHelper;
import de.mogwai.common.i18n.ResourceHelperLocator;

public class DefaultCheckboxMenuItem extends JCheckBoxMenuItem implements I18NAble {

    private String resourceID;

    public DefaultCheckboxMenuItem(String aResourceID) {

        super(aResourceID);
        resourceID = aResourceID;
        initialize();
    }

    public DefaultCheckboxMenuItem(Action aAction) {

        super(aAction);
        initialize();
    }

    @Override
    public void setFont(Font aFont) {

        super.setFont(aFont.deriveFont(Font.PLAIN));
    }

    public String getResourceBundleID() {

        return resourceID;
    }

    public ResourceHelper getResourceHelper() {

        Action theAction = getAction();
        if (theAction instanceof DefaultAction) {
            return ((DefaultAction) theAction).getResourceHelper();
        }
        return ResourceHelperLocator.findResourceHelperFor(this);
    }

    private void initialize() {

        UIInitializer.getInstance().initializeFontAndColors(this);
    }
}
