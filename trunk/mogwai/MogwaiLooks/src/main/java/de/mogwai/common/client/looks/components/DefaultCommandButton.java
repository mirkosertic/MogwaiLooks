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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DefaultCommandButton extends DefaultButton {

    private String m_command;

    private DefaultCommandDelegate m_delegate = new DefaultCommandDelegate(this);

    public DefaultCommandButton() {

        this(null);
    }

    public DefaultCommandButton(String aText) {

        super(aText);
        addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (m_command != null) {
                    m_delegate.execute(m_command);
                }
            }
        });
    }

    public String getCommand() {

        return m_command;
    }

    public void setCommand(String command) {

        m_command = command;
    }
}
