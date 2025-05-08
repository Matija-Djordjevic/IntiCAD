package raf.draft.dsw.commands.roomelementcommands;

import lombok.Getter;
import lombok.NonNull;
import raf.draft.dsw.models.roomelements.RoomElement;

import java.util.UUID;

public abstract class RoomElementCommand implements IRoomElementCommand {
    @Getter
    final UUID Id = UUID.randomUUID();

    @Getter @NonNull
    RoomElement<?> roomElement;
    RoomElementCommand(@NonNull RoomElement<?> roomElement) {
        if (roomElement == null) throw new IllegalArgumentException("RoomElement cannot be null");
        this.roomElement = roomElement;
    }
}
