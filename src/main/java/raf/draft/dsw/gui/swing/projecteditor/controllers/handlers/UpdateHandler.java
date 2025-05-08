package raf.draft.dsw.gui.swing.projecteditor.controllers.handlers;

import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;
import raf.draft.dsw.utils.PathUtils;

public class UpdateHandler {
    ProjectView projectView;
    public UpdateHandler(ProjectView projectView) {
        this.projectView = projectView;
    }
    public void handleUpdate() {
        refreshTabNamesAndHints();
    }

    private void refreshTabNamesAndHints() {
        var tabs = projectView.getTabs();
        for(int i = tabs.getTabCount() - 1; i >= 0; i--){
            var view = (RoomView)tabs.getComponentAt(i);
            var room = view.getRoom();
            tabs.setTitleAt(i, room.getName());
            tabs.setToolTipTextAt(i, PathUtils.toSingleStr(PathUtils.getPath(room)));
        }
    }
}
