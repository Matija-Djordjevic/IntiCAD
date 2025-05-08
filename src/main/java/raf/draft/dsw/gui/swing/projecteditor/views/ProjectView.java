package raf.draft.dsw.gui.swing.projecteditor.views;

import lombok.Getter;
import raf.draft.dsw.commands.ITreeCommandsManager;
import raf.draft.dsw.commands.TreeCommandsManagerAtomic;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.projecteditor.controllers.CrudController;
import raf.draft.dsw.gui.swing.projecteditor.controllers.TabbedPaneChangeListener;
import raf.draft.dsw.gui.swing.projecteditor.models.ProjectViewModel;
import raf.draft.dsw.gui.swing.states.IState;
import raf.draft.dsw.gui.swing.states.StateManager;
import raf.draft.dsw.iterators.ColorGenerator;
import raf.draft.dsw.logging.IMessageListener;
import raf.draft.dsw.logging.LogInfo;
import raf.draft.dsw.logging.LogLevel;
import raf.draft.dsw.models.Building;
import raf.draft.dsw.models.MessageGenerator;
import raf.draft.dsw.models.Project;
import raf.draft.dsw.models.Room;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.utils.HierarchyFetchingUtils;
import raf.draft.dsw.utils.PathUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProjectView extends JPanel {
    @Getter
    Project project = null;
    @Getter
    SideBar SideBar;
    @Getter
    CrudController crudController;
    @Getter
    ProjectEditorActionManager actionManager;
    @Getter
    JTabbedPane tabs;
    @Getter
    TabbedPaneChangeListener tabbedPaneChangeListener;
    @Getter
    ProjectViewModel projectViewModel;

    StateManager stateManager;

    MessageGenerator messageGenerator;


    public ProjectView() { initialize(); }

    public void startAddState(){stateManager.setAddState(); logStateChange("add state");}
    public void startDeleteState(){stateManager.setDeleteState(); logStateChange("remove state");}
    public void startEditRoomState(){stateManager.setEditRoomState(); logStateChange("edit room state");}
    public void startEditRoomElementsState(){stateManager.setEditRoomElementsState(); logStateChange("edit room elements state");}
    public void startMoveState(){stateManager.setMoveState(); logStateChange("move state");}
    public void startResizeState(){stateManager.setResizeState(); logStateChange("resize state");}
    public void startSelectState(){stateManager.setSelectState(); logStateChange("select state");}
    public void startZoomState(){stateManager.setZoomState(); logStateChange("zoom state");}

    private void initialize() {
        tabs = new JTabbedPane();
        projectViewModel = new ProjectViewModel();
        tabs.addChangeListener(tabbedPaneChangeListener = new TabbedPaneChangeListener());

        setLayout(new BorderLayout());
        actionManager = new ProjectEditorActionManager();
        projectViewModel.addSubscriber(actionManager);

        setLayout(new BorderLayout());
        add(tabs, BorderLayout.CENTER);

        SideBar = new SideBar(this);
        add(SideBar, BorderLayout.EAST);

        redraw(null);
        crudController = new CrudController();

        stateManager = new StateManager();
        stateManager.setAddState();

        messageGenerator = ApplicationFramework.getInstance().getMessageGenerator();
    }

    IState getCurrentState() { return stateManager.getCurrentState(); }

    public void PVMouseClicked(RoomView roomView, Point point) {
        getCurrentState().MouseClickedSameSameButDifferentButStillSame(roomView, point);
    }

    public void PVMousePressed(RoomView roomView, Point point) {
        getCurrentState().MousePressedSameSameButDifferentButSame(roomView, point);
    }

    public void PVMouseReleased(RoomView roomView, Point point) {
        getCurrentState().MouseReleasedSameSameButDifferentButStillSame(roomView, point);
    }

    public void PVMouseWheelUp(RoomView roomView, int rotation) {
        getCurrentState().MouseWheelUpSameSameButDifferentButStillSame(roomView, rotation);
    }

    public void PVMouseWheelDown(RoomView roomView, int rotation) {
        getCurrentState().MouseWheelDownSameSameButDifferentButStillSame(roomView, rotation);
    }

    public void PVMouseDragged(RoomView roomView, Point point){
        getCurrentState().MouseDraggedSameSameButDifferentButStillSame(roomView, point);
    }

    public void PVMouseMoved(RoomView roomView, Point point){
        getCurrentState().MouseMovedSameSameButDifferentButStillSame(roomView, point);
    }

    public void redraw(Project proj){
        tabs.removeAll();
        project = proj;
        if(project == null) return;
        var roomsByBuilding = HierarchyFetchingUtils.getDescendentsByAncestor(project, Building.class, Room.class);
        roomsByBuilding.keySet().forEach(building -> addRoomTabs(roomsByBuilding.get(building)));
    }

    public void addRoomTabs(ArrayList<ISpacialModel> rooms) {
        ColorGenerator gen = new ColorGenerator();
        var color = gen.next();
        rooms.forEach(room -> addRoomTab((Room)room, color));
    }

    public void addRoomTab(Room room, Color color, int index) {
        var name = room.getName();

        RoomView roomView = new RoomView(room);

        var path = PathUtils.toSingleStr(PathUtils.getPath(room));
        tabs.insertTab(name, null, roomView, path, index);
        tabs.setBackgroundAt(index, color);
    }

    private void logStateChange(String stateName) { messageGenerator.notify(
            new LogInfo(LogLevel.INFO, "Switched to " + stateName)
    ); }

    private void addRoomTab(Room room, Color color) {
        addRoomTab(room, color, tabs.getTabCount());
    }
}

