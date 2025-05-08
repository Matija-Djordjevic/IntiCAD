package raf.draft.dsw.gui.swing.controller.treeactions;

import raf.draft.dsw.config.Config;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.logging.LogInfo;
import raf.draft.dsw.logging.LogLevel;
import raf.draft.dsw.utils.JsonSerializationUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveAsAction extends AbstractDraftAction {

    // Define the desired file extension
    private static final String FILE_EXTENSION = ".semi";

    public SaveAsAction() {
        putValue(SMALL_ICON, loadIcon("/images/treeactions/save-as.png"));
        putValue(NAME, "Save As");
        putValue(SHORT_DESCRIPTION, "Save projects to the chosen file with a .semi extension.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Project As");
        int userChoice = fileChooser.showSaveDialog(MainFrame.getInstance());

        if (userChoice == JFileChooser.APPROVE_OPTION) {
            File saveFile = fileChooser.getSelectedFile();

            if (!saveFile.getName().toLowerCase().endsWith(FILE_EXTENSION)) {
                saveFile = new File(saveFile.getAbsolutePath() + FILE_EXTENSION);
            }

            var pe = ApplicationFramework.getInstance().getProjectManager().getProjectExplorer();

            try {
                JsonSerializationUtils.saveToFile(pe, saveFile);

                Config.getInstance().setSavePath(saveFile.getAbsolutePath());

                var info = new LogInfo(LogLevel.INFO, "File saved successfully: " + saveFile.getAbsolutePath());
                ApplicationFramework.getInstance().getMessageGenerator().notify(info);

                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "Project saved successfully to:\n" + saveFile.getAbsolutePath(),
                        "Save As Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (Exception ex) {
                var error = new LogInfo(LogLevel.ERROR, "Error saving file: " + ex.getMessage());
                ApplicationFramework.getInstance().getMessageGenerator().notify(error);

                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "An error occurred while saving the project:\n" + ex.getMessage(),
                        "Save Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}
