package raf.draft.dsw.gui.swing.projecteditor.views;

import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SideBar extends JToolBar {
    public SideBar(ProjectView projectView) {
        super(VERTICAL);
        setFloatable(false);

        var am = projectView.getActionManager();

        add(newActionButton(am.getEditRoomAction()));
        am.getEditRoomAction().setEnabled(false);

        add(newActionButton(am.getAddAction()));
        am.getAddAction().setEnabled(false);

        add(newActionButton(am.getEditRoomElementsAction()));
        am.getEditRoomElementsAction().setEnabled(false);

        add(newActionButton(am.getSelectAction()));
        am.getSelectAction().setEnabled(false);

        add(newActionButton(am.getDeleteAction()));
        am.getDeleteAction().setEnabled(false);

        add(newActionButton(am.getMoveAction()));
        am.getMoveAction().setEnabled(false);

        add(newActionButton(am.getCopyAction()));
        am.getCopyAction().setEnabled(false);

        add(newActionButton(am.getPasteAction()));
        am.getPasteAction().setEnabled(false);

        add(newActionButton(am.getResizeAction()));
        am.getResizeAction().setEnabled(false);

        add(newActionButton(am.getRotateRightAction()));
        am.getRotateRightAction().setEnabled(false);

        add(newActionButton(am.getRotateLeftAction()));
        am.getRotateLeftAction().setEnabled(false);

        add(newActionButton(am.getZoomAction()));
        am.getZoomAction().setEnabled(false);

        add(newActionButton(am.getOrganiseMyRoomAction()));
        am.getOrganiseMyRoomAction().setEnabled(false);

        add(newActionButton(am.getUndoAction()));
        am.getUndoAction().setEnabled(false);

        add(newActionButton(am.getRedoAction()));
        am.getRedoAction().setEnabled(false);
    }

    private JButton newActionButton(final AbstractDraftAction action) {
        final var size = new Dimension(20, 20);
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
