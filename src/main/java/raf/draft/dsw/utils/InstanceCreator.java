package raf.draft.dsw.utils;

import raf.draft.dsw.models.roomelements.RoomElement;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;

import java.awt.geom.Point2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class InstanceCreator {
    InstanceCreator(){}


    public static <T extends RoomElement<?>> T getRoomElement(Class<T> roomElementSubClass,
                                                           String name,
                                                           CompositeSpacialModel spacialParent,
                                                           Point2D.Double topLeft,
                                                           double width,
                                                           double height,
                                                           double rotation) {
        Constructor<T> constructor;
        try {
            constructor = roomElementSubClass.getDeclaredConstructor(Point2D.Double.class,
                    double.class,
                    double.class,
                    double.class,
                    String.class,
                    CompositeSpacialModel.class);
            return constructor.newInstance(topLeft, width, height, rotation, name, spacialParent);
        } catch (NoSuchMethodException
                 | InvocationTargetException
                 | InstantiationException
                 | IllegalAccessException e) {
            // TODO handle
            throw new RuntimeException(e);
        }
    }

    public static <T extends ISpacialModel> T getSpacialModel(Class<T> clazz, String name, CompositeSpacialModel spacialParent) {
        Constructor<T> constructor;
        try {
            constructor = clazz.getDeclaredConstructor(String.class, CompositeSpacialModel.class);
            return constructor.newInstance(name, spacialParent);
        } catch (NoSuchMethodException
                 | InvocationTargetException
                 | InstantiationException
                 | IllegalAccessException e) {
            // TODO handle
            throw new RuntimeException(e);
        }
    }
}
