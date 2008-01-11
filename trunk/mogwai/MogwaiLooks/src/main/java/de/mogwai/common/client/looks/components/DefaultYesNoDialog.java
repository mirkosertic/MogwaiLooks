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
import java.awt.event.ActionEvent;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.mogwai.common.client.looks.LooksBundle;
import de.mogwai.common.client.looks.UIInitializer;
import de.mogwai.common.client.looks.components.action.DefaultAction;
import de.mogwai.common.i18n.ResourceHelper;
import de.mogwai.common.i18n.ResourceHelperProvider;

public class DefaultYesNoDialog extends DefaultDialog {

	private String questionResourceID;

	private ResourceHelper looksHelper = ResourceHelper
			.getResourceHelper(LooksBundle.BUNDLE_NAME);

	public final static int YES_OPTION = 1;

	public final static int NO_OPTION = 2;

	private int result = NO_OPTION;

	public DefaultYesNoDialog(Component aParent,
			ResourceHelperProvider aProvider, String aTitleResourceID,
			String aQuestionResourceID) {
		this(aParent, aProvider.getResourceHelper(), aTitleResourceID,
				aQuestionResourceID);
	}

	public DefaultYesNoDialog(Component aParent,
			ResourceHelper aResourceHelper, String aTitleResourceID,
			String aQuestionResourceID) {
		super(aParent, aResourceHelper, aTitleResourceID);
		questionResourceID = aQuestionResourceID;

		initialize();
	}

	private void initialize() {

		setModal(true);

		Container theContent = getContentPane();
		FormLayout theLayout = new FormLayout(
				"2dlu,right:80dlu,2dlu,left:80dlu,,2dlu",
				",2dlu,50dlu,2dlu,p,20dlu");
		CellConstraints theConstraints = new CellConstraints();
		theContent.setLayout(theLayout);

		DefaultLabel theInfo = new DefaultLabel(questionResourceID);
		theInfo.setColon(false);
		theInfo.setHorizontalAlignment(DefaultLabel.CENTER);
		theContent.add(theInfo, theConstraints.xywh(2, 2, 3, 1));

		DefaultAction theYesAction = new DefaultAction(this, looksHelper,
				LooksBundle.YES);
		theYesAction.setCommandName("Yes");
		DefaultButton theYesButton = new DefaultButton();
		theYesButton.setPreferredSize(new Dimension(80, 25));

		DefaultAction theNoAction = new DefaultAction(this, looksHelper,
				LooksBundle.NO);
		theNoAction.setCommandName("No");
		DefaultButton theNoButton = new DefaultButton();
		theNoButton.setPreferredSize(new Dimension(80, 25));

		theContent.add(theYesButton, theConstraints.xy(2, 4));
		theContent.add(theNoButton, theConstraints.xy(4, 4));

		UIInitializer.getInstance().initialize(this);
		pack();

		theYesButton.setAction(theYesAction);
		theNoButton.setAction(theNoAction);
	}

	public int getQuestionResult() {
		setVisible(true);
		return result;
	}

	public void doYes(ActionEvent e) {
		result = YES_OPTION;
		setVisible(false);
	}

	public void doNo(ActionEvent e) {
		result = NO_OPTION;
		setVisible(false);
	}
}
