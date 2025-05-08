package raf.draft.dsw.gui.swing.controller.treeactions;

import raf.draft.dsw.config.Config;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.logging.LogInfo;
import raf.draft.dsw.logging.LogLevel;
import raf.draft.dsw.models.ProjectExplorer;
import raf.draft.dsw.utils.JsonSerializationUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;

public class LoadAction extends AbstractDraftAction {

    // Define the required file extension
    private static final String REQUIRED_EXTENSION = ".semi";

    public LoadAction() {
        putValue(SMALL_ICON, loadIcon("/images/treeactions/load.png")); // Ensure you have a load icon
        putValue(NAME, "Load");
        putValue(SHORT_DESCRIPTION, "Load projects from a .semi file.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Project");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Semi Files (*.semi)", "semi");
        fileChooser.setFileFilter(filter);
        fileChooser.setAcceptAllFileFilterUsed(false); // Disable the "All Files" option

        int userChoice = fileChooser.showOpenDialog(MainFrame.getInstance());

        if (userChoice == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String selectedPath = selectedFile.getAbsolutePath();

            // Verify that the file has a .semi extension
            if (!selectedPath.toLowerCase().endsWith(REQUIRED_EXTENSION)) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "Please select a file with the '" + REQUIRED_EXTENSION + "' extension.",
                        "Invalid File Type",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            var pm = ApplicationFramework.getInstance().getProjectManager();
            var pe = pm.getProjectExplorer();

            if (pm.hasLoadedProject()) {
                int confirmLoad = JOptionPane.showConfirmDialog(
                        MainFrame.getInstance(),
                        "A project is already loaded. Do you want to load a new project?",
                        "Confirm Load",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (confirmLoad != JOptionPane.YES_OPTION) {
                    // User chose not to load a new project; exit the action
                    return;
                }
            }

            try {
                var loadedProjectExplorer = JsonSerializationUtils.loadFromFile(pe.getClass(), selectedFile);

                if (loadedProjectExplorer == null) {
                    throw new Exception("Failed to deserialize the project data.");
                }

                //ApplicationFramework.getInstance().getProjectManager().setProjectExplorer(loadedProjectExplorer);

                Config.getInstance().setSavePath(selectedFile.getAbsolutePath());

                ApplicationFramework.getInstance().getMessageGenerator().notify(
                        new LogInfo(LogLevel.INFO, "Project loaded successfully from: " + selectedFile.getAbsolutePath()));
            } catch (Exception ex) {
                var error = new LogInfo(LogLevel.ERROR, "Error loading file: " + ex.getMessage());
                ApplicationFramework.getInstance().getMessageGenerator().notify(error);
            }
        }
    }
}
