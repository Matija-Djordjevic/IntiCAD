package raf.draft.dsw.factories.commands.roomelements;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import raf.draft.dsw.models.roomelements.RoomElement;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class RoomElementCommandsFactoryBufferBuilder {
    RoomElement<?> roomElement;
    double xDrag, yDrag;
    double newWidth, newHeight;
    double rotateFor;
    final List<RoomElement<?>> roomElementsList = new ArrayList<>();


    public RoomElementCommandsFactoryBufferBuilder setRotation(double rotateFor) {
        this.rotateFor = rotateFor;
        return this;
    }

    public RoomElementCommandsFactoryBufferBuilder setRoomElementsList(@NonNull List<RoomElement<?>> roomElementsList) {
        if(roomElementsList == null) throw new IllegalArgumentException("roomElementsList cannot be null");
        this.roomElementsList.clear();
        this.roomElementsList.addAll(roomElementsList);
        return this;
    }

    public RoomElementCommandsFactoryBufferBuilder setNewWidthAndHeight(double newWidth, double newHeight) {
        if(newHeight <= 0 || newWidth <= 0) throw new IllegalArgumentException("Width and height must be positive");
        this.newWidth = newWidth;
        this.newHeight = newHeight;
        return this;
    }

    public RoomElementCommandsFactoryBufferBuilder setXAndYDrag(double xDrag, double yDrag) {
        this.xDrag = xDrag;
        this.yDrag = yDrag;
        return this;
    }

    public RoomElementCommandsFactoryBufferBuilder setRoomElement(@NonNull RoomElement<?> roomElement) {
        if(roomElement == null) throw new IllegalArgumentException("RoomElement must not be null");

        this.roomElement = roomElement;
        return this;
    }

    protected RoomElementCommandsFactoryBuffer build() {
        if(roomElement == null && roomElementsList.isEmpty())
            throw new IllegalArgumentException("Set RoomElement or RoomElementsList");

        return new RoomElementCommandsFactoryBuffer(
                roomElement,
                xDrag,
                yDrag,
                newWidth,
                newHeight,
                rotateFor,
                MaterialiseRoomElements());
    }

    private ArrayList<RoomElement<?>> MaterialiseRoomElements() { return new ArrayList<>(roomElementsList); }
}
