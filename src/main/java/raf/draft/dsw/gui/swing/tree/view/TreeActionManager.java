package raf.draft.dsw.gui.swing.tree.view;

import lombok.Getter;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.*;

import raf.draft.dsw.gui.swing.controller.treeactions.*;
import raf.draft.dsw.gui.swing.controller.treeactions.SaveAction;
import raf.draft.dsw.models.Project;
import raf.draft.dsw.models.ProjectExplorer;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;

@Getter
public class TreeActionManager {
    ExitAction exitAction;
    AddSpacialModelAction addSpacialModelAction;
    ChangeProjectInfoAction changeProjectInfoAction;
    RemoveSpacialModelAction removeSpacialModelAction;
    AboutUsAction aboutUsAction;
    RenameAction renameAction;
    SaveAsAction saveAsAction;
    SaveAction saveAction;

    public TreeActionManager() {
        initialiseActions();
    }

    private void initialiseActions() {
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();

        addSpacialModelAction = new AddSpacialModelAction();
        addSpacialModelAction.setEnabled(false);

        removeSpacialModelAction = new RemoveSpacialModelAction();
        removeSpacialModelAction.setEnabled(false);

        changeProjectInfoAction = new ChangeProjectInfoAction();
        changeProjectInfoAction.setEnabled(false);

        renameAction = new RenameAction();
        renameAction.setEnabled(false);

        saveAsAction = new SaveAsAction();
        saveAsAction.setEnabled(true);

        saveAction = new SaveAction();
        saveAction.setEnabled(true);
    }

    public void update(SpacialModel model) {
        var isSelected = model != null;
        var checker = ApplicationFramework.getInstance().getChecker();

        var canRemoveAndRename = isSelected && model.getClass() != ProjectExplorer.class;
        getRenameAction().setEnabled(canRemoveAndRename);
        getRemoveSpacialModelAction().setEnabled(canRemoveAndRename);

        var canAdd = false;
        if(model instanceof CompositeSpacialModel cmpModel) canAdd =
                checker.getAllPossibleChildrenTypes(cmpModel.getClass())
                        .stream()
                        .findAny()
                        .isPresent();
        getAddSpacialModelAction().setEnabled(canAdd);

        var canChangePInfo = isSelected && model.getClass() == Project.class;
        getChangeProjectInfoAction().setEnabled(canChangePInfo);
    }
}
