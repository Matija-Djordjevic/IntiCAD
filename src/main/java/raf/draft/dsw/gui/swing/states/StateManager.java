package raf.draft.dsw.gui.swing.states;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StateManager {
    @Getter
    IState currentState = null;

    AddState addState = new AddState();
    DeleteState deleteState = new DeleteState();
    EditRoomState editRoomState = new EditRoomState();
    EditRoomElementsState editRoomElementsState = new EditRoomElementsState();
    MoveState moveState = new MoveState();
    ResizeState resizeState = new ResizeState();
    SelectState selectState = new SelectState();
    ZoomState zoomState = new ZoomState();

    public void setAddState() { currentState = addState; }
    public void setDeleteState() { currentState = deleteState; }
    public void setEditRoomState() { currentState = editRoomState; }
    public void setEditRoomElementsState() { currentState = editRoomElementsState; }
    public void setMoveState() { currentState = moveState; }
    public void setResizeState() { currentState = resizeState; }
    public void setSelectState() { currentState = selectState; }
    public void setZoomState() { currentState = zoomState; }
}
