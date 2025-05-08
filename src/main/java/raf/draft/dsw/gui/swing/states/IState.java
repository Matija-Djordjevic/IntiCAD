package raf.draft.dsw.gui.swing.states;

import raf.draft.dsw.gui.swing.projecteditor.views.RoomView;

import java.awt.*;

public interface IState {
    default void MouseClickedSameSameButDifferentButStillSame(RoomView roomView, Point e) {}
    default void MouseReleasedSameSameButDifferentButStillSame(RoomView roomView, Point e){}
    default void MousePressedSameSameButDifferentButSame(RoomView roomView, Point e){}
    default void MouseMovedSameSameButDifferentButStillSame(RoomView roomView, Point e){}
    default void MouseWheelUpSameSameButDifferentButStillSame(RoomView roomView, int e){}
    default void MouseWheelDownSameSameButDifferentButStillSame(RoomView roomView, int e){}
    default void MouseDraggedSameSameButDifferentButStillSame(RoomView roomView, Point e){}
}
