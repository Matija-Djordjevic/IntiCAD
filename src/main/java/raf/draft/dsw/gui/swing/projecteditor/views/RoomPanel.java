package raf.draft.dsw.gui.swing.projecteditor.views;

import lombok.Setter;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.painters.concretes.RoomPainter;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.models.Room;
import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;
import raf.draft.dsw.observer.ICrudSubscriber;

import javax.swing.*;
import java.awt.*;

public class RoomPanel extends JPanel implements ICrudSubscriber {
    final Room room;
    @Setter
    JScrollPane container;

    public RoomPanel(Room room) {
        this.room = room;
        ApplicationFramework.getInstance().getProjectManager().addSubscriber(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        try {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            boolean hasValidDimensions = room.getWidthCm() > 0 && room.getHeightCm() > 0;
            if (hasValidDimensions) configureZoom(g2d);

            RoomPainter roomPainter = new RoomPainter();
            roomPainter.paint(g2d, room);

        } finally {
            g2d.dispose();
        }
    }

    private void configureZoom(Graphics2D g2d) {
        setZoom(); // Adjusts the preferred size and zoom factor

        // Retrieve the current zoom factor from the StrokeManager
        ProjectView projectView = (ProjectView) MainFrame.getInstance().getProjectView();
        StrokeManager strokeManager = projectView.getProjectViewModel().getLastSelectedRoomView().getStrokeManager();
        double zoom = strokeManager.getZoom();

        // Apply zoom scaling to the Graphics2D context
        g2d.scale(zoom, zoom);
    }

    private void setZoom() {
        int roomHeight = room.getHeightCm();
        int roomWidth = room.getWidthCm();

        int availableWidth = getWidth();
        int availableHeight = getHeight();

        var pv = (ProjectView) MainFrame.getInstance().getProjectView();
        var manager = pv.getProjectViewModel().getLastSelectedRoomView().getStrokeManager();
        double zoom = manager.zoom;

        if (zoom == 0.0 && roomWidth > 0 && roomHeight > 0) {
            double widthScalar = (double) availableWidth / roomWidth;
            double heightScalar = (double) availableHeight / roomHeight;

            zoom = Math.max(widthScalar, heightScalar);
            manager.setZoom(zoom);
        }

        if (roomWidth > 0 && roomHeight > 0) {
            setPreferredSize(
                    new Dimension((int) (roomWidth * zoom), (int) (roomHeight * zoom))
            );
        } else {
            // When room has no dimensions, use the available component size
            setPreferredSize(new Dimension(availableWidth, availableHeight));
        }

        revalidate();
        repaint();

        if (container != null) { // Ensure container is set
            container.getViewport().setView(this);
        }
    }


    public void updateAddCrud(ISpacialModel parent, ISpacialModel child) {
        if (parent instanceof Room roomParent && roomParent.equals(room)) {
            repaint();
        }
    }

    @Override
    public void updateDeleteCrud(ISpacialModel model) {
        if(model.getParent() instanceof CompositeSpacialModel parent
                && parent.equals(room)) {
            repaint();
        }
    }

    @Override
    public void updateUpdateCrud(ISpacialModel model) {
        if(model == room) {
            repaint();
        } else if (model instanceof RoomElement<?> roomElement
                && room.getChildrenCollection().getById(roomElement.getGuid()) != null){
            repaint();
        }
    }
}
