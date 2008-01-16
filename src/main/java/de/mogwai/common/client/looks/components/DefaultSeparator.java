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
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import de.mogwai.common.i18n.I18NAble;
import de.mogwai.common.i18n.ResourceHelper;

public class DefaultSeparator extends DefaultPanel implements I18NAble {

    private static final class TitledSeparatorLayout implements LayoutManager {

        private final boolean centerSeparators;

        private TitledSeparatorLayout(boolean centerSeparators) {
            this.centerSeparators = centerSeparators;
        }

        public void addLayoutComponent(String name, Component comp) {
        }

        public void removeLayoutComponent(Component comp) {
        }

        public Dimension minimumLayoutSize(Container parent) {
            return preferredLayoutSize(parent);
        }

        public Dimension preferredLayoutSize(Container parent) {
            Component label = getLabel(parent);
            Dimension labelSize = label.getPreferredSize();
            Insets insets = parent.getInsets();
            int width = labelSize.width + insets.left + insets.right;
            int height = labelSize.height + insets.top + insets.bottom;
            return new Dimension(width, height);
        }

        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                Dimension size = parent.getSize();
                Insets insets = parent.getInsets();
                int width = size.width - insets.left - insets.right;

                JLabel label = getLabel(parent);
                Dimension labelSize = label.getPreferredSize();
                int labelWidth = labelSize.width;
                int labelHeight = labelSize.height;
                Component separator1 = parent.getComponent(1);
                int separatorHeight = separator1.getPreferredSize().height;

                FontMetrics metrics = label.getFontMetrics(label.getFont());
                int ascent = metrics.getMaxAscent();
                int hGapDlu = centerSeparators ? 3 : 1;
                int hGap = 5;
                int vOffset = centerSeparators ? 1 + (labelHeight - separatorHeight) / 2 : ascent - separatorHeight / 2;

                int alignment = label.getHorizontalAlignment();
                int y = insets.top;
                if (alignment == SwingConstants.LEFT) {
                    int x = insets.left;
                    label.setBounds(x, y, labelWidth, labelHeight);
                    x += labelWidth;
                    x += hGap;
                    int separatorWidth = size.width - insets.right - x;
                    separator1.setBounds(x, y + vOffset, separatorWidth, separatorHeight);
                } else if (alignment == SwingConstants.RIGHT) {
                    int x = insets.left + width - labelWidth;
                    label.setBounds(x, y, labelWidth, labelHeight);
                    x -= hGap;
                    x--;
                    int separatorWidth = x - insets.left;
                    separator1.setBounds(insets.left, y + vOffset, separatorWidth, separatorHeight);
                } else {
                    int xOffset = (width - labelWidth - 2 * hGap) / 2;
                    int x = insets.left;
                    separator1.setBounds(x, y + vOffset, xOffset - 1, separatorHeight);
                    x += xOffset;
                    x += hGap;
                    label.setBounds(x, y, labelWidth, labelHeight);
                    x += labelWidth;
                    x += hGap;
                    Component separator2 = parent.getComponent(1);
                    int separatorWidth = size.width - insets.right - x;
                    separator2.setBounds(x, y + vOffset, separatorWidth, separatorHeight);
                }
            }
        }

        private DefaultLabel getLabel(Container parent) {
            return (DefaultLabel) parent.getComponent(0);
        }

    }

    private DefaultLabel label;

    public DefaultSeparator() {
        label = new DefaultLabel();
        configure();
    }

    public DefaultSeparator(String aResourceKey) {
        label = new DefaultLabel(aResourceKey);
        configure();
    }

    private void configure() {

        label.setHorizontalAlignment(SwingConstants.LEFT);
        label.setColon(false);

        setLayout(new TitledSeparatorLayout(true));
        add(label);
        add(new JSeparator());
    }

    public String getResourceBundleID() {
        return label.getResourceBundleID();
    }

    public ResourceHelper getResourceHelper() {
        return label.getResourceHelper();
    }

    public void setText(String aText) {
        label.setText(aText);
    }
}