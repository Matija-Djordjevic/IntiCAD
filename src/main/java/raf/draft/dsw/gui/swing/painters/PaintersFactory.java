package raf.draft.dsw.gui.swing.painters;

import lombok.NoArgsConstructor;
import raf.draft.dsw.gui.swing.painters.concretes.*;
import raf.draft.dsw.models.roomelements.Door;
import raf.draft.dsw.models.roomelements.RoomElement;

import javax.swing.Painter;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class PaintersFactory {
    public MyPainter getPainter(RoomElement<?> element) {
        return switch (element.getClass().getSimpleName()) {
            case "Bed" -> new BedPainter();
            case "Boiler" -> new BoilerPainter();
            case "Closet" -> new ClosetPainter();
            case "Door" -> new DoorPainter();
            case "Shower" -> new ShowerPainter();
            case "Sink" -> new SinkPainter();
            case "Table" -> new TablePainter();
            case "Toilet" -> new ToiletPainter();
            case "WashingMachine" -> new WashingMachinePainter();
            case "Room" -> new RoomPainter();
            default -> throw new IllegalArgumentException(
                    "No painter registered for type: " + element.getClass().getSimpleName());
        };
    }
}
