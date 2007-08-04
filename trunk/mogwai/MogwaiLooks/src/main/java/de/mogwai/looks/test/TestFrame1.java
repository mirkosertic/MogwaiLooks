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
package de.mogwai.looks.test;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.mogwai.looks.components.DefaultFrame;
import de.mogwai.looks.components.DefaultList;
import de.mogwai.looks.components.DefaultPanel;
import de.mogwai.looks.components.list.DefaultListModel;

public class TestFrame1 extends DefaultFrame {

	public TestFrame1() {

		initialize();
	}

	private void initialize() {

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		DefaultPanel thePanel = new DefaultPanel();
		FormLayout theLayout = new FormLayout("2dlu,fill:80dlu,2dlu",
				"2dlu,fill:200dlu");
		CellConstraints theConstraints = new CellConstraints();
		thePanel.setLayout(theLayout);
		DefaultList<String> theList = new DefaultList<String>();
		DefaultListModel<String> theData = theList.getModel();
		theData.add("Hallo1");
		theData.add("Hallo1");
		theData.fireListDataChanged();
		thePanel.add(theList.getScrollPane(), theConstraints.xy(2, 2));
		getDefaultFrameContent().setDetailComponent(thePanel);
		pack();
	}

	public static void main(String args[]) {

		TestFrame1 theFrame = new TestFrame1();
		theFrame.setVisible(true);
	}
}
