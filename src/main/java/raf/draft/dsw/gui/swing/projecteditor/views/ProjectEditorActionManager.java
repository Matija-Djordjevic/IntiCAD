package raf.draft.dsw.gui.swing.projecteditor.views;

import lombok.Getter;
import raf.draft.dsw.Main;
import raf.draft.dsw.gui.swing.controller.projecteditoractions.*;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.observer.ISubscriber;

@Getter
public class ProjectEditorActionManager implements ISubscriber {
    AddAction addAction;
    CopyAction copyAction;
    DeleteAction deleteAction;
    EditRoomElementsAction editRoomElementsAction;
    EditRoomAction editRoomAction;
    MoveAction moveAction;
    PasteAction pasteAction;
    ResizeAction resizeAction;
    RotateRightAction rotateRightAction;
    RotateLeftAction rotateLeftAction;
    SelectAction selectAction;
    ZoomAction zoomAction;
    OrganiseMyRoomAction organiseMyRoomAction;
    UndoAction undoAction;
    RedoAction redoAction;

    public ProjectEditorActionManager() { initialiseActions();}

    private void initialiseActions() {
        var commandsFactory = MainFrame.getInstance().getRoomElementCommandsFactory();

        addAction               = new AddAction();
        copyAction              = new CopyAction();
        deleteAction            = new DeleteAction(commandsFactory);
        editRoomElementsAction  = new EditRoomElementsAction();
        editRoomAction          = new EditRoomAction();
        moveAction              = new MoveAction();
        pasteAction             = new PasteAction();
        resizeAction            = new ResizeAction();
        rotateRightAction       = new RotateRightAction(commandsFactory);
        rotateLeftAction        = new RotateLeftAction(commandsFactory);
        selectAction            = new SelectAction();
        zoomAction              = new ZoomAction();
        organiseMyRoomAction    = new OrganiseMyRoomAction();
        undoAction              = new UndoAction();
        redoAction              = new RedoAction();
    }

    @Override
    public void update(Object notification) { refresh(); }

    public void refresh() {
        var projectView = (ProjectView) MainFrame.getInstance().getProjectView();

        var roomHasDimensions = false;
        var lastSelectedRoomView = projectView.getProjectViewModel().getLastSelectedRoomView();
        var selected = lastSelectedRoomView.getSelected().stream();
        var coped = lastSelectedRoomView.getCopied().stream();
        var first2Selected = selected.limit(2).toList();
        var first2Copied = coped.limit(2).toList();

        var isOneSelected = first2Selected.size() == 1;
        var isMoreSelected = first2Selected.size() == 2;
        var someSelected = isOneSelected || isMoreSelected;

        var isOneCopied = first2Copied.size() == 1;
        var isMoreCopied = first2Copied.size() == 2;
        var someCopied = isOneCopied || isMoreCopied;

        var room = lastSelectedRoomView.getRoom();
        roomHasDimensions = room.getWidthCm() != 0 && room.getHeightCm() != 0;

        editRoomAction.setEnabled(!roomHasDimensions);

        addAction.setEnabled(roomHasDimensions);
        editRoomElementsAction.setEnabled(roomHasDimensions);
        copyAction.setEnabled(roomHasDimensions && someSelected);
        deleteAction.setEnabled(roomHasDimensions);
        moveAction.setEnabled(roomHasDimensions && someSelected);
        pasteAction.setEnabled(roomHasDimensions && someCopied);
        resizeAction.setEnabled(roomHasDimensions && isOneSelected);
        rotateRightAction.setEnabled(roomHasDimensions && isOneSelected);
        rotateLeftAction.setEnabled(roomHasDimensions && isOneSelected);
        selectAction.setEnabled(roomHasDimensions);
        organiseMyRoomAction.setEnabled(roomHasDimensions);
        zoomAction.setEnabled(roomHasDimensions);

        var commandsManager = projectView.getProjectViewModel().getLastSelectedRoomView().getCommandsManager();
        var canUndo = commandsManager.canUndo();
        var canRedo = commandsManager.canRedo();
        undoAction.setEnabled(roomHasDimensions && canUndo);
        redoAction.setEnabled(roomHasDimensions && canRedo);

        if(!roomHasDimensions) projectView.startEditRoomState();
    }
}
