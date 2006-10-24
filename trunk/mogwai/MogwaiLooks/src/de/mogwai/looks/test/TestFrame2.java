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
package de.mogwai.looks.test;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import de.mogwai.looks.components.DefaultCheckBoxList;
import de.mogwai.looks.components.DefaultCheckBoxListModel;
import de.mogwai.looks.components.DefaultFrame;
import de.mogwai.looks.components.DefaultListModel;
import de.mogwai.looks.components.DefaultPanel;
import de.mogwai.looks.tools.DefaultSelectableItem;

public class TestFrame2 extends DefaultFrame {

    public TestFrame2() {

        initialize();
    }

    private void initialize() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        DefaultPanel thePanel = new DefaultPanel();
        FormLayout theLayout = new FormLayout("2dlu,fill:80dlu,2dlu",
                "2dlu,fill:200dlu");
        CellConstraints theConstraints = new CellConstraints();
        thePanel.setLayout(theLayout);
        DefaultCheckBoxList<String> theList = new DefaultCheckBoxList<String>();
        DefaultCheckBoxListModel<String> theData = theList.getModel();
        for (int count = 0; count < 120; count++) {
            theData.add("Hallo " + count);
        }
        theData.fireTableDataChanged();
        thePanel.add(theList.getScrollPane(), theConstraints.xy(2, 2));
        getDefaultFrameContent().setDetailComponent(thePanel);
        // UIInitializer.getInstance().initialize(this);
        pack();
    }

    public static void main(String args[]) {

        TestFrame2 theFrame = new TestFrame2();
        theFrame.setVisible(true);
    }
}
