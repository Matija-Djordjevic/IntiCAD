package raf.draft.dsw.gui.swing.controller.projecteditoractions;

import raf.draft.dsw.commands.CommandExecuteException;
import raf.draft.dsw.factories.commands.roomelements.IRoomElementCommandsFactory;
import raf.draft.dsw.gui.swing.controller.AbstractDraftAction;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class DeleteAction extends AbstractDraftAction {
    IRoomElementCommandsFactory commandsFactory;

    public DeleteAction(IRoomElementCommandsFactory commandsFactory) {
        putValue(SMALL_ICON, loadIcon("/images/projecteditactions/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete room elements");

        this.commandsFactory = commandsFactory;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        var projView = (ProjectView)MainFrame.getInstance().getProjectView();
        var roomView = projView.getProjectViewModel().getLastSelectedRoomView();
        if(roomView == null) return;

        var selected = roomView.getSelected();
        if(selected.isEmpty()) {
            projView.startDeleteState();
            return;
        }

        var deleteManyCommand = commandsFactory.buildCommand(
                "remove_many",
                opt ->
                        opt
                                .setRoomElementsList(selected)
        );

        try{
            roomView.getCommandsManager().addCommand(deleteManyCommand);
        }catch(CommandExecuteException ex){
            System.out.println(ex.getMessage());
            return;
        }
    }
}
