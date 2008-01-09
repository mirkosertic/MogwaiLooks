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
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;

import de.mogwai.common.client.looks.components.renderer.DefaultTableHeaderRenderer;
import de.mogwai.i18n.I18NAble;
import de.mogwai.i18n.I18NInitializer;

public class UIInitializer {

	private static UIInitializer me;

	private Font defaultFont;

	private Color defaultBackgroundColor;

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

	public Color getDefaultDisabledTextFieldColor() {

		return defaultDisabledTextFieldColor;
	}

	public Color getDefaultErrorColor() {

		return defaultErrorColor;
	}

	public Color getDefaultListNonSelectionForeground() {

		return defaultListNonSelectionForeground;
	}

	public Color getDefaultListSelectionForeground() {

		return defaultListSelectionForeground;
	}

	public Color getDefaultBackgroundColor() {

		return defaultBackgroundColor;
	}

	public Color getDefaultDesktopPaneBackgroundColor() {

		return defaultDesktopPaneBackgroundColor;
	}

	public Font getDefaultFont() {

		return defaultFont;
	}

	public Color getDefaultListNonSelectionBackground() {

		return defaultListNonSelectionBackground;
	}

	public Color getDefaultListSelectionBackground() {

		return defaultListSelectionBackground;
	}

	private UIInitializer() {

		defaultFont = new Font("SansSerif", Font.PLAIN, 12);
		defaultBackgroundColor = new Color(236, 233, 216);
		UIManager.put("TabbedPane.tabAreaBackground", defaultBackgroundColor);
		UIManager.put("TabbedPane.selected", defaultBackgroundColor);
		UIManager.put("TabbedPane.highlight", defaultBackgroundColor);
		// UIManager.put("TabbedPane.darkShadow", defaultBackgroundColor);
		UIManager.put("TextField.inactiveForeground",
				defaultDisabledTextFieldColor);

		UIManager.put("TabbedPane.shadow", Color.black);
		UIManager.put("TabbedPane.selectHighlight", Color.gray);
	}

	public static UIInitializer getInstance() {

		if (me == null) {
			me = new UIInitializer();
		}
		return me;
	}

	public void initialize(Container aRoot) {

		if (aRoot == null)
			return;
		if (aRoot instanceof JFrame) {
			initialize(((JFrame) aRoot).getJMenuBar());
		}
		initializeComponent(aRoot);
		boolean normal = true;
		if (aRoot instanceof JMenuBar) {
			normal = false;
			JMenuBar theMenu = (JMenuBar) aRoot;
			for (int i = 0; i < theMenu.getMenuCount(); i++)
				initialize(theMenu.getMenu(i));
		}
		if (aRoot instanceof JMenu) {
			normal = false;
			JMenu theMenu = (JMenu) aRoot;
			for (int i = 0; i < theMenu.getItemCount(); i++)
				initialize(theMenu.getItem(i));
		}
		if (normal) {
			Component[] theComponents = aRoot.getComponents();
			for (int i = 0; i < theComponents.length; i++) {
				initializeComponent(theComponents[i]);
				if (theComponents[i] instanceof Container) {
					initialize((Container) theComponents[i]);
				}
			}
		}
	}

	public void initializeFontAndColors(Component aComponent) {

		if (aComponent instanceof JComponent)
			((JComponent) aComponent).updateUI();
		aComponent.setFont(defaultFont);
		if (aComponent instanceof JPanel)
			aComponent.setBackground(defaultBackgroundColor);
		if (aComponent instanceof AbstractButton)
			aComponent.setBackground(defaultBackgroundColor);
		if (aComponent instanceof JDesktopPane)
			aComponent.setBackground(defaultDesktopPaneBackgroundColor);
		if (aComponent instanceof JComboBox)
			aComponent.setBackground(defaultBackgroundColor);
		if (aComponent instanceof JLabel)
			aComponent.setBackground(defaultBackgroundColor);
		if (aComponent instanceof JMenuBar)
			aComponent.setBackground(defaultBackgroundColor);
		if (aComponent instanceof JMenu)
			aComponent.setBackground(defaultBackgroundColor);
		if (aComponent instanceof JMenuItem)
			aComponent.setBackground(defaultBackgroundColor);
		if (aComponent instanceof JPopupMenu)
			aComponent.setBackground(defaultBackgroundColor);
		if (aComponent instanceof JTableHeader)
			aComponent.setBackground(defaultBackgroundColor);
		if (aComponent instanceof JToolBar)
			aComponent.setBackground(defaultBackgroundColor);
		if (aComponent instanceof JTable) {
			JTable theTable = (JTable) aComponent;
			for (int count = 0; count < theTable.getColumnCount(); count++)
				theTable.getColumnModel().getColumn(count).setHeaderRenderer(
						new DefaultTableHeaderRenderer());
			initializeComponent(theTable.getTableHeader());
		}
	}

	public void initializeComponent(Component aComponent) {

		initializeFontAndColors(aComponent);
		if (aComponent instanceof I18NAble) {
			I18NInitializer.initialize((I18NAble) aComponent);
		}
		if (aComponent instanceof JComponent) {
			Border theBorder = ((JComponent) aComponent).getBorder();
			if (theBorder instanceof I18NAble) {
				I18NInitializer.initialize((I18NAble) theBorder);
			}
		}
	}

	public Color getDefaultBorderColor() {

		return defaultBorderColor;
	}

	public Color getDefaultTableGridColor() {

		return defaultTableGridColor;
	}

	public Color getDefaultInternalFrameBorderColor() {

		return defaultInternalFrameBorderColor;
	}
}