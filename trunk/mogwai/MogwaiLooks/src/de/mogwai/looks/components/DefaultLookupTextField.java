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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JPanel;

public class DefaultLookupTextField extends JPanel {

    private DefaultTextField textField=new DefaultTextField();
    private DefaultButton button=new DefaultButton();
    
    public DefaultLookupTextField() {
        initialize();
    }
    
    private void initialize() {
        setLayout(new BorderLayout());
        add(textField,BorderLayout.CENTER);
        add(button,BorderLayout.EAST);
        
        button.setPreferredSize(new Dimension(21,21));
        button.setMinimumSize(new Dimension(21,21));        
        button.setMaximumSize(new Dimension(21,21));        
        button.setSize(new Dimension(21,21));
        
        button.setText("?");
        button.setMargin(new Insets(0,0,0,0));
    }
    
    @Override
    public void setEnabled(boolean bstatus) {
        textField.setEnabled(bstatus);
        button.setEnabled(bstatus);
    }
    
    public DefaultTextField getTextField() {
        return textField;
    }
    
    public DefaultButton getButton() {
        return button;
    }
}
