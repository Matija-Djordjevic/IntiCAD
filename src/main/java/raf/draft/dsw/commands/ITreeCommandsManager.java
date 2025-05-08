package raf.draft.dsw.commands;

import java.util.List;

public interface ITreeCommandsManager extends ICommandsManager {
    void redoAt(int index) throws IndexOutOfBoundsException;
    List<ICommand> getAvailableCommands();
}
