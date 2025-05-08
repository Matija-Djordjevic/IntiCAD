package raf.draft.dsw.factories.commands.roomelements;

import raf.draft.dsw.models.roomelements.RoomElement;

import java.util.ArrayList;

public record RoomElementCommandsFactoryBuffer(
        RoomElement<?> roomElement,
        double xDrag,
        double yDrag,
        double newWidth,
        double newHeight,
        double rotateFor,
        ArrayList<RoomElement<?>> roomElementsList){ }
