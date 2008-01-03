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
package de.mogwai.common.client.looks.components.treetable;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultTreeSelectionModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import de.mogwai.common.client.looks.UIInitializer;
import de.mogwai.common.client.looks.components.DefaultTable;
import de.mogwai.common.client.looks.components.renderer.DefaultCellRenderer;

public class DefaultTreeTable extends DefaultTable {

	protected TreeTableCellRenderer tree;

	public DefaultTreeTable() {

		super();

		setDefaultEditor(DefaultTreeTableModel.class, new TreeTableCellEditor());
		setShowGrid(false);
		setIntercellSpacing(new Dimension(0, 0));
	}

	public void setModel(DefaultTreeTableModel aModel) {

		int selectedRow = getSelectedRow();

		tree = new TreeTableCellRenderer(aModel);
		super.setModel(new DefaultTreeTableModelAdapter(aModel, tree));
		tree.setSelectionModel(new DefaultTreeSelectionModel() {

			{
				setSelectionModel(listSelectionModel);
			}
		});
		tree.setRowHeight(getRowHeight());
		setDefaultEditor(DefaultTreeTableModel.class, new TreeTableCellEditor());
		setShowGrid(false);
		setIntercellSpacing(new Dimension(0, 0));
		setDefaultRenderer(DefaultTreeTableModel.class, tree);

		aModel.getDescriptor().configureTable(this);

		expandAll(true);

		getSelectionModel().setSelectionInterval(selectedRow, selectedRow);
	}

	public DefaultTreeTableNode getSelectedNode() {
		if (tree != null) {
			TreePath thePath = tree.getSelectionPath();
			if (thePath == null)
				return null;

			return (DefaultTreeTableNode) thePath.getLastPathComponent();
		} else
			return null;
	}

	@Override
	public int getEditingRow() {

		return (getColumnClass(editingColumn) == DefaultTreeTableModel.class) ? -1
				: editingRow;
	}

	public void expandAll(boolean expand) {
		DefaultTreeTableNode root = (DefaultTreeTableNode) tree.getModel()
				.getRoot();

		expandAll(new TreePath(root), expand);
	}

	private void expandAll(TreePath parent, boolean expand) {
		DefaultTreeTableNode node = (DefaultTreeTableNode) parent
				.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (int i = 0; i < node.getChildCount(); i++) {
				TreePath path = parent.pathByAddingChild(node.getChild(i));
				expandAll(path, expand);
			}
		}

		if (expand) {
			tree.expandPath(parent);
		} else {
			tree.collapsePath(parent);
		}
	}

	private class TreeTableCellRenderer extends JTree implements
			TableCellRenderer {

		protected int visibleRow;

		private UIInitializer initializer = UIInitializer.getInstance();

		public TreeTableCellRenderer(TreeModel model) {
			super(model);
			setCellRenderer(DefaultCellRenderer.getInstance());
			UIInitializer.getInstance().initializeComponent(this);
		}

		@Override
		public void setBounds(int x, int y, int w, int h) {

			super.setBounds(x, 0, w, DefaultTreeTable.this.getHeight());
		}

		@Override
		public void paint(Graphics g) {

			g.translate(0, -visibleRow * getRowHeight());
			super.paint(g);
		}

		public Component getTableCellRendererComponent(JTable table,
				Object value, boolean isSelected, boolean hasFocus, int row,
				int column) {

			if (isSelected) {
				setBackground(initializer.getDefaultListSelectionBackground());
				setForeground(initializer.getDefaultListSelectionForeground());
			} else {
				setBackground(initializer
						.getDefaultListNonSelectionBackground());
				setForeground(initializer
						.getDefaultListNonSelectionForeground());
			}
			visibleRow = row;
			return this;
		}
	}

	private class TreeTableCellEditor extends AbstractCellEditor implements
			TableCellEditor {

		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int r, int c) {
			return tree;
		}

		public Object getCellEditorValue() {
			return null;
		}
	}
}
