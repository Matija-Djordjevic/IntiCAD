package raf.draft.dsw.gui.swing.controller.treeactions.handlers;

import raf.draft.dsw.models.structure.ISpacialModel;

import javax.swing.*;

public interface IAddRoomElementHandler {
    void doAddElement(Class<? extends ISpacialModel> type,
                                               JTextField txtBox,
                                               JLabel warningLbl,
                                               JFrame frame);
}
