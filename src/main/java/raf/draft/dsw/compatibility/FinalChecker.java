package raf.draft.dsw.compatibility;

import raf.draft.dsw.core.Tuple;
import raf.draft.dsw.models.roomelements.*;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.Building;
import raf.draft.dsw.models.Project;
import raf.draft.dsw.models.ProjectExplorer;
import raf.draft.dsw.models.Room;

import java.util.ArrayList;

public class FinalChecker extends SpacialModelsCompatibilityChecker {
    @Override
    public ArrayList<Tuple<Class<? extends CompositeSpacialModel>, Class<? extends ISpacialModel>>> getPossibleParentChildPairsTypes() {
        var list = new ArrayList<Tuple<Class<? extends CompositeSpacialModel>, Class<? extends ISpacialModel>>>();

        {
            list.add(new Tuple<>(ProjectExplorer.class, Project.class));
            list.add(new Tuple<>(Project.class, Building.class));
            list.add(new Tuple<>(Project.class, Room.class));
            list.add(new Tuple<>(Building.class, Room.class));
            list.add(new Tuple<>(Room.class, Bed.class));
            list.add(new Tuple<>(Room.class, Boiler.class));
            list.add(new Tuple<>(Room.class, Closet.class));
            list.add(new Tuple<>(Room.class, Door.class));
            list.add(new Tuple<>(Room.class, Shower.class));
            list.add(new Tuple<>(Room.class, Sink.class));
            list.add(new Tuple<>(Room.class, Table.class));
            list.add(new Tuple<>(Room.class, Toilet.class));
            list.add(new Tuple<>(Room.class, WashingMachine.class));
        }

        return list;
    }

    @Override
    public ArrayList<Class<? extends ISpacialModel>> getNonDeletableConcreteTypes() {
        var list = new ArrayList<Class<? extends ISpacialModel>>();

        {
            list.add(ProjectExplorer.class);
        }

        return list;
    }

    @Override
    public ArrayList<Class<? extends ISpacialModel>> getAllConcreteTypes() {
        var types = new  ArrayList<Class<? extends ISpacialModel>>();

        {
            types.add(Project.class);
            types.add(ProjectExplorer.class);
            types.add(Building.class);
            types.add(Room.class);
            types.add(RoomElement.class);
        }

        return types;
    }

}
