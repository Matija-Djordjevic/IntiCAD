package raf.draft.dsw.commands.roomelementcommands;

import raf.draft.dsw.commands.CompositeAtomicCommand;
import raf.draft.dsw.commands.ICommand;
import raf.draft.dsw.commands.LoggingCommand;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.spacialmodelcollections.PositionalSpacialModelCollection;

import java.util.ArrayList;

public class MoveRoomElementsCompositeCommand
        extends CompositeAtomicCommand
        implements IRoomElementCompositeCommand {

    public MoveRoomElementsCompositeCommand(ArrayList<ICommand> commands) {
        super(commands);

        var optional = commands.stream().findFirst();
        if(optional.isEmpty()) return;

        var firstCommand = (MoveRoomElementCommand) optional.get();
        var parent = firstCommand.getRoomElement().getParent();

        if(!commands.stream().allMatch(c -> predicate(c, parent)))
            throw new IllegalArgumentException("MoveRoomElementsCompositeCommand can only be used with MoveRoomElementsCompositeCommand");
    }

    private boolean predicate(ICommand command, CompositeSpacialModel parent) {
        MoveRoomElementCommand unwrappedCommand;
        if(command instanceof MoveRoomElementCommand move) unwrappedCommand = move;
        else if (command instanceof LoggingCommand log && log.unwrapCommand() instanceof MoveRoomElementCommand move) unwrappedCommand = move;
        else return false;

        return unwrappedCommand.getRoomElement().getParent() == parent;
    }

    @Override
    protected boolean doFinalCheck() {
        var list = new ArrayList<RoomElement<?>>();
        commands.forEach(command -> {
            var concreteComand = command;

            if(command instanceof LoggingCommand loggingCommand) concreteComand = loggingCommand.unwrapCommand();

            if(!(concreteComand instanceof MoveRoomElementCommand moveRoomElementCommand)) return;
            list.add(moveRoomElementCommand.roomElement);
        });

        var firstCommand = (MoveRoomElementCommand) commands.getFirst();
        var re = firstCommand.getRoomElement();
        var parent = re.getParent();

        var everyChild = (PositionalSpacialModelCollection) parent.getChildrenCollection();
        return list.stream().noneMatch(everyChild::overlapWithAnyButSelf);
    }


    @Override
    public String getName() { return this.getClass().getSimpleName(); }
}
