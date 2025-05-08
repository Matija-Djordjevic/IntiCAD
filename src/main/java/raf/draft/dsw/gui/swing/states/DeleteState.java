package raf.draft.dsw.gui.swing.states;

import raf.draft.dsw.commands.roomelementcommands.RemoveRoomElementCommand;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.structure.spacialmodelcollections.PositionalSpacialModelCollection;

import java.awt.*;

public class DeleteState  implements IState {

    @Override
    public void MouseClickedSameSameButDifferentButStillSame(RoomView roomView, Point e) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var collection = (PositionalSpacialModelCollection) pv.getProjectViewModel().getLastSelectedRoomView().getRoom().getChildrenCollection();


        var forDeletion = collection
                .findOnPoint(e)
                .stream()
                .findFirst()
                .orElse(null);

        if (forDeletion == null) return;
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();
        rv.getCommandsManager().addCommand(new RemoveRoomElementCommand(forDeletion));
    }
}
