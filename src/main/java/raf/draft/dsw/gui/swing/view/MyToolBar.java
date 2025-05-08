package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyToolBar extends JToolBar {

    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);

        var am = MainFrame.getInstance().getActionManager();

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setOpaque(false);
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.setOpaque(false);

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        // right
        rightPanel.add(newActionButton(am.getExitAction()));
        rightPanel.add(newActionButton(am.getAboutUsAction()));

        // left
        leftPanel.add(newActionButton(am.getChangeProjectInfoAction()));
        leftPanel.add(newActionButton(am.getRenameAction()));
        leftPanel.add(newActionButton(am.getAddSpacialModelAction()));
        leftPanel.add(newActionButton(am.getRemoveSpacialModelAction()));
        leftPanel.add(newActionButton(am.getSaveAsAction()));
        leftPanel.add(newActionButton(am.getSaveAction()));
    }

    private JButton newActionButton(final AbstractDraftAction action) {
        final var size = new Dimension(40, 40);
        final var hoverColor = Color.LIGHT_GRAY;

        var button = new JButton(action);
        button.setText(null);
        button.setPreferredSize(size);
        button.setBackground(hoverColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button.getAction().isEnabled()) button.setContentAreaFilled(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setContentAreaFilled(false);
            }
        });

        return button;
    }
}
