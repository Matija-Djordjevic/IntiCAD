package raf.draft.dsw.gui.swing.painters.concretes;

import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.gui.swing.utils.DrawingUtils;
import raf.draft.dsw.models.Room;
import raf.draft.dsw.utils.PathUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class SetDimensionRoomPromptPainter implements MyPainter<Room> {
    @Override
    public void paint(Graphics2D g2d, Room element) {
        var fontSize = 20;

        var font = new Font("Ariel", Font.PLAIN, fontSize);
        g2d.setFont(font);

        var point = new Point(element.getWidthCm() / 2, element.getHeightCm() / 2);

        var enterDimensionsMsg = "Please set room dimensions to begin to draw";

        var info = new ArrayList<>(Arrays.asList(
                PathUtils.getPathAsStr(element),
                enterDimensionsMsg));
        DrawingUtils.drawStringsAroundPoint(point, info, g2d);
    }
}
