package raf.draft.dsw.commands;

import java.util.UUID;

public interface ICommand {
    void redo() throws CommandExecuteException;
    void undo() throws CommandExecuteException;

    String getUndoDescription();
    String getRedoDescription();
    String getName();

    UUID getId();
}
