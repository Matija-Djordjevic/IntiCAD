package raf.draft.dsw.commands;

public interface ICommandsManager {
    void addCommand(ICommand command);

    void redo();
    void undo();

    boolean canUndo();
    boolean canRedo();
}
