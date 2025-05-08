package raf.draft.dsw.iterators;

import java.awt.*;
import java.util.*;

public class ColorGenerator implements Iterator<Color> {
    private final Stack<Color> colors = new Stack<>();
    private int additionalSize = 300;

    public ColorGenerator(int additionalSize) {
        this.additionalSize = additionalSize;
        setUpColorsList();
    }

    public ColorGenerator() { setUpColorsList(); }

    private void setUpColorsList() {
        colors.clear();

        addCustomColors();

        Random rand = new Random();

        var newColors = new ArrayList<>(Arrays.asList(new Object[additionalSize]))
                .stream()
                .map(_ -> new Color(
                        rand.nextInt(0, 256),
                        rand.nextInt(0, 256),
                        rand.nextInt(0, 256)))
                .toList();

        colors.addAll(newColors);
        Collections.shuffle(colors);
    }

    public static Color NavyBlue() { return Color.decode("#000080"); }
    public static Color ZimaBlue() { return Color.decode("#5BC2E7"); }
    public static Color PerfectBlue() { return new Color(62, 144, 171); }
    public static Color StickyNoteYellow() { return Color.decode("#FFFF88"); }
    public static Color CujuNas() { return Color.decode("#d19a02"); }

    private void addCustomColors() {
        colors.add(ZimaBlue());
        colors.add(PerfectBlue());
        colors.add(NavyBlue());
        colors.add(StickyNoteYellow());
    }

    @Override
    public boolean hasNext() {
        return !colors.isEmpty();
    }

    @Override
    public Color next() {
        return colors.pop();
    }
}
