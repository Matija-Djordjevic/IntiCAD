package raf.draft.dsw.gui.swing.controller;

import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractDraftAction {
    public AboutUsAction() {
        putValue(SMALL_ICON, loadIcon("/images/treeactions/about-us.png"));
        putValue(NAME, "About Us");
        putValue(SHORT_DESCRIPTION, "About Us");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final String matija = "2. Matija Djordjevic SI 133";
        final String isidora = "1. Isidora Mitrovic SI 119";
        final String prefixMsg = "Studenti koji rade na projektu:";

        final String message = String.format(
            "%s\n%s\n%s\n",
            prefixMsg, isidora, matija
        );

        JOptionPane.showMessageDialog(MainFrame.getInstance(), message);
    }
}
