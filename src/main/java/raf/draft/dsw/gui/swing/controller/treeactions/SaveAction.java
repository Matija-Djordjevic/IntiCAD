package raf.draft.dsw.gui.swing.controller.treeactions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.config.Config;
import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.logging.LogInfo;
import raf.draft.dsw.logging.LogLevel;
import raf.draft.dsw.utils.JsonSerializationUtils;
import raf.draft.dsw.utils.StringUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveAction extends AbstractDraftAction {

    public SaveAction() {
        putValue(SMALL_ICON, loadIcon("/images/treeactions/save.png"));
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save the projects.");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String savePath = Config.getInstance().getSavePath();

        if (StringUtils.isNullOrEmpty(savePath)) {
            int response = JOptionPane.showConfirmDialog(
                    MainFrame.getInstance(),
                    "Save path is not set. Chose save location?",
                    "Save Path Not Set",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (response == JOptionPane.YES_OPTION) {
                Action saveAsAction = new SaveAsAction();
                saveAsAction.actionPerformed(e);
            }
            return;
        }

        File saveFile = new File(savePath);


        var pe = ApplicationFramework.getInstance().getProjectManager().getProjectExplorer();

        try {
            JsonSerializationUtils.saveToFile(pe, saveFile);
            Config.getInstance().setSavePath(savePath);

            ApplicationFramework.getInstance().getMessageGenerator().notify(
                    new LogInfo(LogLevel.INFO, "Project saved successfully to: " + saveFile.getAbsolutePath()));

        } catch (Exception ex) {
            ApplicationFramework.getInstance().getMessageGenerator().notify(
                    new LogInfo(LogLevel.ERROR, "Error saving project: " + ex.getMessage()));
        }
    }
}
