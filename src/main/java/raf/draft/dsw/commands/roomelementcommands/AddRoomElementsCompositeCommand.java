package raf.draft.dsw.commands.roomelementcommands;

import raf.draft.dsw.commands.CompositeAtomicCommand;
import raf.draft.dsw.commands.ICommand;

import java.util.ArrayList;

public class AddRoomElementsCompositeCommand
        extends CompositeAtomicCommand
        implements IRoomElementCompositeCommand {
    public AddRoomElementsCompositeCommand(ArrayList<ICommand> commands) { super(commands); }

    @Override
    protected boolean doFinalCheck() { return true; }

    @Override
    public String getName() { return "Adding Room Elements Composite Command"; }
}
