package raf.draft.dsw.gui.swing.projecteditor.controllers;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.projecteditor.controllers.handlers.AddHandler;
import raf.draft.dsw.gui.swing.projecteditor.controllers.handlers.RemoveHandler;
import raf.draft.dsw.gui.swing.projecteditor.controllers.handlers.UpdateHandler;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.observer.ICrudSubscriber;

public class CrudController implements ICrudSubscriber {
    public CrudController() {
        ApplicationFramework.getInstance().getProjectManager().addSubscriber(this);
    }

    @Override
    public void updateAddCrud(ISpacialModel parent, ISpacialModel child) {
        var projectView = (ProjectView) MainFrame.getInstance().getProjectView();
        if(projectView.getProject() == null) return;

        var addHandler = new AddHandler(projectView);
        addHandler.handleAdd(parent, child);
    }

    @Override
    public void updateDeleteCrud(ISpacialModel child) {
        var projectView = (ProjectView) MainFrame.getInstance().getProjectView();
        if(projectView.getProject() == null) return;

        var removeHandler = new RemoveHandler(projectView);
        removeHandler.handleRemove(child);
    }

    @Override
    public void updateUpdateCrud(ISpacialModel model) {
        var projectView = (ProjectView) MainFrame.getInstance().getProjectView();
        if(projectView.getProject() == null) return;

        var updateHandler = new UpdateHandler(projectView);
        updateHandler.handleUpdate();
    }
}
