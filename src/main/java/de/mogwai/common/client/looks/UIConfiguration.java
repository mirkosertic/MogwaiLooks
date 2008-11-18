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
package de.mogwai.common.client.looks;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

public class UIConfiguration {

    private Font defaultFont = new Font("SansSerif", Font.PLAIN, 12);

    private Color defaultBackgroundColor = new Color(236, 233, 216);

    private Color defaultDesktopPaneBackgroundColor = Color.lightGray;

    private Color defaultTableGridColor = Color.lightGray;

    private Color defaultListSelectionBackground = new Color(253, 247, 175);

    private Color defaultListNonSelectionBackground = Color.white;

    private Color defaultListSelectionForeground = Color.black;

    private Color defaultListNonSelectionForeground = Color.black;

    private Color defaultBorderColor = Color.lightGray;

    private Color defaultInternalFrameBorderColor = Color.BLACK;

    private Color defaultErrorColor = Color.red;

    private Color defaultDisabledTextFieldColor = Color.darkGray;

    private Color defaultTabbedPaneShadow = Color.black;

    private Color defaultTabbedPaneHighlight = Color.gray;

    private boolean applyConfiguration = true;

    /**
     * @return the applyConfiguration
     */
    public boolean isApplyConfiguration() {
        return applyConfiguration;
    }

    /**
     * @param applyConfiguration
     *            the applyConfiguration to set
     */
    public void setApplyConfiguration(boolean applyConfiguration) {
        this.applyConfiguration = applyConfiguration;
    }

    /**
     * @return the defaultBackgroundColor
     */
    public Color getDefaultBackgroundColor() {
        return defaultBackgroundColor;
    }

    /**
     * @param defaultBackgroundColor
     *            the defaultBackgroundColor to set
     */
    public void setDefaultBackgroundColor(Color defaultBackgroundColor) {
        this.defaultBackgroundColor = defaultBackgroundColor;
    }

    /**
     * @return the defaultBorderColor
     */
    public Color getDefaultBorderColor() {
        return defaultBorderColor;
    }

    /**
     * @param defaultBorderColor
     *            the defaultBorderColor to set
     */
    public void setDefaultBorderColor(Color defaultBorderColor) {
        this.defaultBorderColor = defaultBorderColor;
    }

    /**
     * @return the defaultDesktopPaneBackgroundColor
     */
    public Color getDefaultDesktopPaneBackgroundColor() {
        return defaultDesktopPaneBackgroundColor;
    }

    /**
     * @param defaultDesktopPaneBackgroundColor
     *            the defaultDesktopPaneBackgroundColor to set
     */
    public void setDefaultDesktopPaneBackgroundColor(Color defaultDesktopPaneBackgroundColor) {
        this.defaultDesktopPaneBackgroundColor = defaultDesktopPaneBackgroundColor;
    }

    /**
     * @return the defaultDisabledTextFieldColor
     */
    public Color getDefaultDisabledTextFieldColor() {
        return defaultDisabledTextFieldColor;
    }

    /**
     * @param defaultDisabledTextFieldColor
     *            the defaultDisabledTextFieldColor to set
     */
    public void setDefaultDisabledTextFieldColor(Color defaultDisabledTextFieldColor) {
        this.defaultDisabledTextFieldColor = defaultDisabledTextFieldColor;
    }

    /**
     * @return the defaultErrorColor
     */
    public Color getDefaultErrorColor() {
        return defaultErrorColor;
    }

    /**
     * @param defaultErrorColor
     *            the defaultErrorColor to set
     */
    public void setDefaultErrorColor(Color defaultErrorColor) {
        this.defaultErrorColor = defaultErrorColor;
    }

    /**
     * @return the defaultFont
     */
    public Font getDefaultFont() {
        return defaultFont;
    }

    /**
     * @param defaultFont
     *            the defaultFont to set
     */
    public void setDefaultFont(Font defaultFont) {
        this.defaultFont = defaultFont;
    }

    /**
     * @return the defaultInternalFrameBorderColor
     */
    public Color getDefaultInternalFrameBorderColor() {
        return defaultInternalFrameBorderColor;
    }

    /**
     * @param defaultInternalFrameBorderColor
     *            the defaultInternalFrameBorderColor to set
     */
    public void setDefaultInternalFrameBorderColor(Color defaultInternalFrameBorderColor) {
        this.defaultInternalFrameBorderColor = defaultInternalFrameBorderColor;
    }

    /**
     * @return the defaultListNonSelectionBackground
     */
    public Color getDefaultListNonSelectionBackground() {
        return defaultListNonSelectionBackground;
    }

    /**
     * @param defaultListNonSelectionBackground
     *            the defaultListNonSelectionBackground to set
     */
    public void setDefaultListNonSelectionBackground(Color defaultListNonSelectionBackground) {
        this.defaultListNonSelectionBackground = defaultListNonSelectionBackground;
    }

    /**
     * @return the defaultListNonSelectionForeground
     */
    public Color getDefaultListNonSelectionForeground() {
        return defaultListNonSelectionForeground;
    }

    /**
     * @param defaultListNonSelectionForeground
     *            the defaultListNonSelectionForeground to set
     */
    public void setDefaultListNonSelectionForeground(Color defaultListNonSelectionForeground) {
        this.defaultListNonSelectionForeground = defaultListNonSelectionForeground;
    }

    /**
     * @return the defaultListSelectionBackground
     */
    public Color getDefaultListSelectionBackground() {
        return defaultListSelectionBackground;
    }

    /**
     * @param defaultListSelectionBackground
     *            the defaultListSelectionBackground to set
     */
    public void setDefaultListSelectionBackground(Color defaultListSelectionBackground) {
        this.defaultListSelectionBackground = defaultListSelectionBackground;
    }

    /**
     * @return the defaultListSelectionForeground
     */
    public Color getDefaultListSelectionForeground() {
        return defaultListSelectionForeground;
    }

    /**
     * @param defaultListSelectionForeground
     *            the defaultListSelectionForeground to set
     */
    public void setDefaultListSelectionForeground(Color defaultListSelectionForeground) {
        this.defaultListSelectionForeground = defaultListSelectionForeground;
    }

    /**
     * @return the defaultTableGridColor
     */
    public Color getDefaultTableGridColor() {
        return defaultTableGridColor;
    }

    /**
     * @param defaultTableGridColor
     *            the defaultTableGridColor to set
     */
    public void setDefaultTableGridColor(Color defaultTableGridColor) {
        this.defaultTableGridColor = defaultTableGridColor;
    }

    /**
     * @return the defaultTabbedPaneHighlight
     */
    public Color getDefaultTabbedPaneHighlight() {
        return defaultTabbedPaneHighlight;
    }

    /**
     * @param defaultTabbedPaneHighlight
     *            the defaultTabbedPaneHighlight to set
     */
    public void setDefaultTabbedPaneHighlight(Color defaultTabbedPaneHighlight) {
        this.defaultTabbedPaneHighlight = defaultTabbedPaneHighlight;
    }

    /**
     * @return the defaultTabbedPaneShadow
     */
    public Color getDefaultTabbedPaneShadow() {
        return defaultTabbedPaneShadow;
    }

    /**
     * @param defaultTabbedPaneShadow
     *            the defaultTabbedPaneShadow to set
     */
    public void setDefaultTabbedPaneShadow(Color defaultTabbedPaneShadow) {
        this.defaultTabbedPaneShadow = defaultTabbedPaneShadow;
    }

    public Map<String, Object> getUIManagerConfig() {
        Map<String, Object> theMap = new HashMap<String, Object>();

        theMap.put("TabbedPane.tabAreaBackground", getDefaultBackgroundColor());
        theMap.put("TabbedPane.selected", getDefaultBackgroundColor());
        theMap.put("TabbedPane.focus", getDefaultBackgroundColor());
        theMap.put("TabbedPane.highlight", getDefaultBackgroundColor());
        theMap.put("TextField.inactiveForeground", getDefaultDisabledTextFieldColor());
        theMap.put("TabbedPane.shadow", getDefaultTabbedPaneShadow());
        theMap.put("TabbedPane.selectHighlight", getDefaultTabbedPaneHighlight());
//        theMap.put("TabbedPane.borderHightlightColor", getDefaultBackgroundColor());
//        theMap.put("TabbedPane.contentBorderInsets", new Insets(1,1,1,1));
        return theMap;
    }
}