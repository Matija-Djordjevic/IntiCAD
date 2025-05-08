package raf.draft.dsw.gui.swing.controller.projecteditoractions;

import raf.draft.dsw.commands.CommandExecuteException;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.factories.commands.roomelements.IRoomElementCommandsFactory;
import raf.draft.dsw.gui.swing.projecteditor.views.ProjectView;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.logging.LogInfo;
import raf.draft.dsw.logging.LogLevel;
import raf.draft.dsw.models.roomelements.RoomElement;


public class RotateHelper {
    // TODO fix
    public static void doRotate(double degrees, IRoomElementCommandsFactory commandsFactory) {
        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var rv = pv.getProjectViewModel().getLastSelectedRoomView();

        var selectedElement = (RoomElement<?>) pv.getProjectViewModel().getLastSelectedRoomView()
                .getSelected()
                .stream()
                .findFirst()
                .orElse(null);
        if(selectedElement == null) return;

        var rotateCommand = commandsFactory.buildCommand(
                "rotate",
                opt ->
                        opt
                                .setRoomElement(selectedElement)
                                .setRotation(degrees));

        try{
            rv.getCommandsManager().addCommand(rotateCommand);
        }catch (CommandExecuteException e){
            log(e.getMessage());
            return;
        }
    }

    private static void log(String msg){
        var mg = ApplicationFramework.getInstance().getMessageGenerator();
        mg.notify(new LogInfo(LogLevel.INFO, msg));
    }
}
