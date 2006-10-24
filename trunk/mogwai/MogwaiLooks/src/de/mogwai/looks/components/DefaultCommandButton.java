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

                if (m_command != null)
                    m_delegate.execute(m_command);
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
