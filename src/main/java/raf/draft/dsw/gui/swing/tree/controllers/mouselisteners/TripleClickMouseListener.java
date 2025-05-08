package raf.draft.dsw.gui.swing.tree.controllers.mouselisteners;

import raf.draft.dsw.gui.swing.view.MainFrame;

import java.awt.event.MouseEvent;

public class TripleClickMouseListener extends AbstractTreeMouseAdapter {
    @Override
    void performAction(MouseEvent e) { MainFrame.getInstance().getActionManager().getRenameAction().actionPerformed(null); }

    @Override
    int clicksRequired(){return 3;}
}