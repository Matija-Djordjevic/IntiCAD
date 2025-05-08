package raf.draft.dsw.factories.commands.roomelements;

import raf.draft.dsw.commands.ICommand;

import java.util.function.Consumer;

public interface IRoomElementCommandsFactory {
    ICommand buildCommand(String commandDesc,
                          Consumer<RoomElementCommandsFactoryBufferBuilder> options);
}
