package raf.draft.dsw.gui.swing.controller.projecteditoractions;

import org.jetbrains.annotations.NotNull;
import raf.draft.dsw.commands.ICommand;
import raf.draft.dsw.commands.TreeCommandsManagerAtomic;
import raf.draft.dsw.config.Config;
import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.ProjectManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RedoAction extends AbstractDraftAction {
    public RedoAction() {
        putValue(SMALL_ICON, loadIcon("/images/projecteditactions/redo.png"));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var config = Config.getInstance();
        if(config.getSettings().isUseUndoRedoTree()) performWithTree();
        else performLinearly();
    }

    public void performLinearly(){
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();
        rv.getCommandsManager().redo();
    }

    public void performWithTree() {
        ToolTipManager.sharedInstance().setInitialDelay(240);
        ToolTipManager.sharedInstance().setDismissDelay(1_000_000);

        JDialog dialog = new JDialog(MainFrame.getInstance(), "Select command to Redo", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        scrollPane.setPreferredSize(new Dimension(450, 300));

        SwingUtilities.invokeLater(() -> {
            JScrollBar hBar = scrollPane.getHorizontalScrollBar();
            int middle = (hBar.getMaximum() - hBar.getMinimum()) / 2;
            hBar.setValue(middle);
        });

        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();
        var commands = rv.getCommandsManager().getAvailableCommands();

        for (int i = 0; i < commands.size(); i++) {
            final int index = i;
            JButton button = getButton(commands, index);

            button.addActionListener(event -> {
                onClick(index, rv);
                dialog.dispose();
            });

            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }

        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(MainFrame.getInstance());
        dialog.setVisible(true);
    }

    private static @NotNull JButton getButton(java.util.List<ICommand> commands, int index) {
        var command = commands.get(index);
        var buttonName = command.getRedoDescription();

        String displayName = buttonName.contains("\n") ? buttonName.split("\n")[0] : buttonName;
        if (displayName.length() > 50) {
            displayName = displayName.substring(0, 50) + "...";
        }

        String safeButtonName = buttonName.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
        String tooltipText = "<html>" + safeButtonName.replace("\n", "<br>") + "</html>";

        JButton button = new JButton(displayName);
        button.setToolTipText(tooltipText);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
        return button;
    }

    private void onClick(int index, RoomView rv) {
        rv.getCommandsManager().redoAt(index);
    }
}
