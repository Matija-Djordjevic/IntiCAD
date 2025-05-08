package raf.draft.dsw.commands.roomelementcommands;

import raf.draft.dsw.commands.CompositeAtomicCommand;
import raf.draft.dsw.commands.ICommand;

import java.util.ArrayList;

public class RemoveRoomElementsCompositeCommand
        extends CompositeAtomicCommand
        implements IRoomElementCompositeCommand {
    public RemoveRoomElementsCompositeCommand(ArrayList<ICommand> commands) { super(commands); }

    @Override
    protected boolean doFinalCheck() { return true; }

    @Override
    public String getName() { return "Remove Room Elements Composite Command"; }
}
