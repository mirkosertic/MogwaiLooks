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
package de.mogwai.common.client.looks.test;

import javax.swing.BorderFactory;
import javax.swing.JTabbedPane;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import de.mogwai.common.client.looks.UIInitializer;
import de.mogwai.common.client.looks.components.DefaultFrame;
import de.mogwai.common.client.looks.components.DefaultList;
import de.mogwai.common.client.looks.components.DefaultPanel;
import de.mogwai.common.client.looks.components.DefaultTabbedPane;
import de.mogwai.common.client.looks.components.list.DefaultListModel;

public class TestFrame3 extends DefaultFrame {

    public TestFrame3() {

        initialize();
    }

    private void initialize() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        DefaultTabbedPane thePane = new DefaultTabbedPane();
        
        DefaultPanel thePanel = new DefaultPanel();
        FormLayout theLayout = new FormLayout("2dlu,fill:80dlu,2dlu", "2dlu,fill:200dlu");
        CellConstraints theConstraints = new CellConstraints();
        thePanel.setLayout(theLayout);
        DefaultList<String> theList = new DefaultList<String>();
        DefaultListModel<String> theData = theList.getModel();
        theData.add("Hallo1");
        theData.add("Hallo1");
        theData.fireListDataChanged();
        thePanel.add(theList.getScrollPane(), theConstraints.xy(2, 2));

        thePane.add("Lulu", thePanel);
        
        getDefaultFrameContent().setDetailComponent(thePane);
        
        UIInitializer.getInstance().initialize(this);
        pack();
    }

    public static void main(String[] args) {
        
        UIInitializer.getInstance();

        TestFrame3 theFrame = new TestFrame3();
        theFrame.setVisible(true);
    }
}
