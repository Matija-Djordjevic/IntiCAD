package raf.draft.dsw.factories.commands.roomelements;

import org.jetbrains.annotations.NotNull;
import raf.draft.dsw.commands.ICommand;
import raf.draft.dsw.commands.LoggingCommand;
import raf.draft.dsw.commands.roomelementcommands.*;
import raf.draft.dsw.logging.IMessageAnnouncer;
import raf.draft.dsw.logging.LogInfo;

import java.util.ArrayList;
import java.util.function.Consumer;

public class RoomElementCommandsFactory implements IRoomElementCommandsFactory{
    final IMessageAnnouncer<LogInfo> announcer;

    public RoomElementCommandsFactory(IMessageAnnouncer<LogInfo> anouncer) {
        this.announcer = anouncer;
    }

    @Override
    public ICommand buildCommand(String commandDesc,
                                            Consumer<RoomElementCommandsFactoryBufferBuilder> options) {
        var buffer = getOptions(options);
        return new LoggingCommand(
                doBuildCommand(commandDesc, buffer),
                announcer);
    }

    private static @NotNull IRoomElementCommand doBuildCommand(String commandDesc, RoomElementCommandsFactoryBuffer buffer) {
        return switch (commandDesc) {
            case "add" -> new AddRoomElementCommand(buffer.roomElement());
            case "remove" -> new RemoveRoomElementCommand(buffer.roomElement());
            case "rotate" -> new RotateRoomElementCommand(buffer.roomElement(), buffer.rotateFor());
            case "resize" -> new ResizeRoomElementCommand(buffer.roomElement(), buffer.newWidth(), buffer.newHeight());
            case "move" -> new MoveRoomElementCommand(buffer.roomElement(), buffer.xDrag(), buffer.yDrag());
            case "move_many" -> getMoveMany(buffer);
            case "remove_many" -> getRemoveMany(buffer);
            case "add_many" -> getAddMany(buffer);
            default -> throw new IllegalArgumentException("Unknown command: " + commandDesc);
        };
    }

    private static @NotNull IRoomElementCommand getAddMany(RoomElementCommandsFactoryBuffer buffer) {
        var commands = new ArrayList<ICommand>();
        buffer.roomElementsList().forEach(el ->
                commands.add(new AddRoomElementCommand(el)));
        return new AddRoomElementsCompositeCommand(commands);
    }

    static @NotNull IRoomElementCommand getMoveMany(RoomElementCommandsFactoryBuffer buffer) {
        var commands = new ArrayList<ICommand>();
        buffer.roomElementsList().forEach(el ->
                commands.add(new MoveRoomElementCommand(el, buffer.xDrag(), buffer.yDrag())));

        return new MoveRoomElementsCompositeCommand(commands);
    }

    static @NotNull IRoomElementCommand getRemoveMany(RoomElementCommandsFactoryBuffer buffer) {
        var commands = new ArrayList<ICommand>();
        buffer.roomElementsList().forEach(el ->
                commands.add(new RemoveRoomElementCommand(el)));

        return new RemoveRoomElementsCompositeCommand(commands);
    }

    static @NotNull RoomElementCommandsFactoryBuffer getOptions(Consumer<RoomElementCommandsFactoryBufferBuilder> options) {
        var builder = new RoomElementCommandsFactoryBufferBuilder();
        options.accept(builder);
        return builder.build();
    }
}
