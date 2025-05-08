package raf.draft.dsw.gui.swing.tree.controllers;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.event.ActionListener;

public class DraftTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {
    public DraftTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

}
