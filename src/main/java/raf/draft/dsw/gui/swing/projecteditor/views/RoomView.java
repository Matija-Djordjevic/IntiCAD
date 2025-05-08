package raf.draft.dsw.gui.swing.projecteditor.views;

import lombok.Getter;
import lombok.Setter;
import raf.draft.dsw.commands.ICommandsManager;
import raf.draft.dsw.commands.ITreeCommandsManager;
import raf.draft.dsw.commands.TreeCommandsManagerAtomic;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.core.Tuple;
import raf.draft.dsw.gui.swing.painters.MyPainter;
import raf.draft.dsw.gui.swing.painters.PaintersFactory;
import raf.draft.dsw.gui.swing.projecteditor.controllers.RoomMouseListener;
import raf.draft.dsw.gui.swing.utils.DrawingUtils;
import raf.draft.dsw.gui.swing.utils.RoundColoredRactangle;
import raf.draft.dsw.models.Room;
import raf.draft.dsw.models.roomelements.*;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;
import raf.draft.dsw.observer.ICrudSubscriber;
import raf.draft.dsw.utils.PathUtils;

import javax.swing.*;
import java.util.ArrayList;

public class RoomView extends JScrollPane {
    @Getter
    ITreeCommandsManager commandsManager;

    @Getter
    final Room room;
    static final int padding = 10;

    @Getter @Setter
    StrokeManager strokeManager;

    @Getter
    JPanel roomPanel;

    @Getter
    final ArrayList<RoomElement<?>> selected = new ArrayList<>();

    @Getter
    final ArrayList<ISpacialModel> copied = new ArrayList<>();

    public RoomView(Room room) {
        super(new RoomPanel(room) {});
        this.room = room;
        this.strokeManager = new StrokeManager();
        roomPanel = (RoomPanel) getViewport().getView();
        ((RoomPanel)roomPanel).setContainer(this);

        var mrl = new RoomMouseListener();
        roomPanel.addMouseListener(mrl);
        roomPanel.addMouseMotionListener(mrl);
        roomPanel.addMouseWheelListener(mrl);

        commandsManager = new TreeCommandsManagerAtomic();

        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }
}
