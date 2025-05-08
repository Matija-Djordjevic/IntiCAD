package raf.draft.dsw.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getAddSpacialModelAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getRemoveSpacialModelAction());

        this.add(fileMenu);
    }
}
