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
package de.mogwai.common.client.looks.components.navigation;

import java.awt.event.ActionEvent;

import de.mogwai.common.client.looks.components.DefaultFrameContent;
import de.mogwai.common.client.looks.components.action.ActionEventProcessor;
import de.mogwai.common.client.looks.components.action.ActionEventProcessorHelper;
import de.mogwai.common.client.looks.components.action.DefaultAction;
import de.mogwai.i18n.ResourceHelper;
import de.mogwai.i18n.ResourceHelperProvider;

public class DefaultNavigatableFrameContent extends DefaultFrameContent
		implements ActionEventProcessor, ResourceHelperProvider {

	public final static int TOOLBAR_NONE = 0;

	public final static int TOOLBAR_EXIT = 1;

	public final static int TOOLBAR_SAVE = 2;

	public final static int TOOLBAR_DELETE = 4;

	public final static int TOOLBAR_NEW = 8;

	public final static int TOOLBAR_FIRST = 16;

	public final static int TOOLBAR_PRIOR = 32;

	public final static int TOOLBAR_NEXT = 64;

	public final static int TOOLBAR_LAST = 128;

	public final static int TOOLBAR_SEARCH = 256;

	public final static int TOOLBAR_UNDO = 512;

	public final static int TOOLBAR_CHECK = 1024;

	public final static int TOOLBAR_COMPLETE = TOOLBAR_EXIT + TOOLBAR_SAVE
			+ TOOLBAR_DELETE + TOOLBAR_NEW + TOOLBAR_FIRST + TOOLBAR_PRIOR
			+ TOOLBAR_NEXT + TOOLBAR_LAST + TOOLBAR_SEARCH + TOOLBAR_UNDO;

	public final static int TOOLBAR_EXITUNDOSAVE = TOOLBAR_EXIT + TOOLBAR_SAVE
			+ TOOLBAR_UNDO;

	public final static int TOOLBAR_EXITANDCHECK = TOOLBAR_EXIT + TOOLBAR_CHECK;

	private ResourceHelper helper;

	protected int toolbarConfiguration;

	protected String toolbarBundleName;

	protected DefaultAction exitAction;

	protected DefaultAction saveAction;

	protected DefaultAction undoAction;

	protected DefaultAction deleteAction;

	protected DefaultAction newAction;

	protected DefaultAction firstAction;

	protected DefaultAction priorAction;

	protected DefaultAction nextAction;

	protected DefaultAction lastAction;

	protected DefaultAction searchAction;

	protected DefaultAction checkAction;

	public DefaultNavigatableFrameContent(String aBundleName,
			String aToolbarBundleName, int aToolbarConfiguration) {
		helper = ResourceHelper.getResourceHelper(aBundleName);
		toolbarConfiguration = aToolbarConfiguration;
		toolbarBundleName = aToolbarBundleName;

		initializeToolbar();
	}

	public ResourceHelper getResourceHelper() {
		return helper;
	}

	@Override
	protected void initializeToolbar() {

		boolean added = false;
		if ((toolbarConfiguration & TOOLBAR_EXIT) > 0) {
			exitAction = new DefaultAction(this, toolbarBundleName, "EXIT");
			exitAction.setCommandName("Exit");
			getToolbar().add(exitAction);
			added = true;
		}
		if (added) {
			added = false;
			getToolbar().addSeparator();
		}

		if ((toolbarConfiguration & TOOLBAR_SAVE) > 0) {
			saveAction = new DefaultAction(this, toolbarBundleName, "SAVE");
			saveAction.setCommandName("Save");
			getToolbar().add(saveAction);
			added = true;
		}
		if ((toolbarConfiguration & TOOLBAR_UNDO) > 0) {
			undoAction = new DefaultAction(this, toolbarBundleName, "UNDO");
			undoAction.setCommandName("Undo");
			getToolbar().add(undoAction);
			added = true;
		}
		if ((toolbarConfiguration & TOOLBAR_DELETE) > 0) {
			deleteAction = new DefaultAction(this, toolbarBundleName, "DELETE");
			deleteAction.setCommandName("Delete");
			getToolbar().add(deleteAction);
			added = true;
		}
		if ((toolbarConfiguration & TOOLBAR_NEW) > 0) {
			newAction = new DefaultAction(this, toolbarBundleName, "NEW");
			newAction.setCommandName("New");
			getToolbar().add(newAction);
			added = true;
		}
		if (added) {
			added = false;
			getToolbar().addSeparator();
		}
		if ((toolbarConfiguration & TOOLBAR_FIRST) > 0) {
			firstAction = new DefaultAction(this, toolbarBundleName, "FIRST");
			firstAction.setCommandName("First");
			getToolbar().add(firstAction);
			added = true;
		}
		if ((toolbarConfiguration & TOOLBAR_PRIOR) > 0) {
			priorAction = new DefaultAction(this, toolbarBundleName, "PRIOR");
			priorAction.setCommandName("Prior");
			getToolbar().add(priorAction);
			added = true;
		}
		if ((toolbarConfiguration & TOOLBAR_NEXT) > 0) {
			nextAction = new DefaultAction(this, toolbarBundleName, "NEXT");
			nextAction.setCommandName("Next");
			getToolbar().add(nextAction);
			added = true;
		}
		if ((toolbarConfiguration & TOOLBAR_LAST) > 0) {
			lastAction = new DefaultAction(this, toolbarBundleName, "LAST");
			lastAction.setCommandName("Last");
			getToolbar().add(lastAction);
			added = true;
		}
		if (added) {
			added = false;
			getToolbar().addSeparator();
		}
		if ((toolbarConfiguration & TOOLBAR_SEARCH) > 0) {
			searchAction = new DefaultAction(this, toolbarBundleName, "SEARCH");
			searchAction.setCommandName("Search");
			getToolbar().add(searchAction);
			added = true;
		}
		if (added) {
			added = false;
			getToolbar().addSeparator();
		}
		if ((toolbarConfiguration & TOOLBAR_CHECK) > 0) {
			checkAction = new DefaultAction(this, toolbarBundleName, "CHECK");
			checkAction.setCommandName("Check");
			getToolbar().add(checkAction);
			added = true;
		}

	}

	public void processActionEvent(ActionEvent e) {
		ActionEventProcessorHelper.invoke(this, e);
	}

	@Override
	public void setModified(boolean bModified) {
		super.setModified(bModified);

		if (saveAction != null)
			saveAction.setEnabled(bModified);

		if (undoAction != null)
			undoAction.setEnabled(bModified);

		if (exitAction != null)
			exitAction.setEnabled(!bModified);

		if (deleteAction != null)
			deleteAction.setEnabled(!bModified && !checkIfNew());

		if (newAction != null)
			newAction.setEnabled(!bModified);

		if (firstAction != null)
			firstAction.setEnabled(!bModified);

		if (priorAction != null)
			priorAction.setEnabled(!bModified);

		if (nextAction != null)
			nextAction.setEnabled(!bModified);

		if (lastAction != null)
			lastAction.setEnabled(!bModified);

		if (searchAction != null)
			searchAction.setEnabled(checkIfNew() && bModified
					&& supportsSearch());
	}

	public boolean supportsSearch() {
		return false;
	}

	public boolean checkIfNew() {
		return false;
	}

	public void doSave(ActionEvent e) {
		setModified(false);
	}

	public void doUndo(ActionEvent e) {
	}

	public void doDelete(ActionEvent e) {
		doNew(e);
	}

	public void doNew(ActionEvent e) {
		setModified(false);
		searchAction.setEnabled(supportsSearch());
	}

	public void doFirst(ActionEvent e) {
	}

	public void doPrior(ActionEvent e) {
	}

	public void doNext(ActionEvent e) {
	}

	public void doLast(ActionEvent e) {
	}

	public void doSearch(ActionEvent e) {
	}

	public void doCheck(ActionEvent e) {
	}
}
