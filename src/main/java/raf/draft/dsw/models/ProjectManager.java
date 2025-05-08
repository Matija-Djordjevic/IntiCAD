package raf.draft.dsw.models;

import lombok.Getter;
import lombok.NonNull;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.models.structure.CompositeSpacialModel;
import raf.draft.dsw.models.structure.ISpacialModel;
import raf.draft.dsw.models.structure.SpacialModel;
import raf.draft.dsw.observer.ICrudPublisher;
import raf.draft.dsw.observer.ICrudSubscriber;

import java.util.ArrayList;
import java.util.function.Consumer;


public class ProjectManager implements ICrudPublisher {
    final ArrayList<ICrudSubscriber> subscribers = new ArrayList<>();

    @Getter
    final ProjectExplorer projectExplorer;
    final String projectExplorerName = "ProjectExplorer";

    public ProjectManager() {
        projectExplorer = new ProjectExplorer(projectExplorerName);
    }

    public boolean hasLoadedProject() {return projectExplorer.getChildrenCollection().count() != 0; }

    public boolean add(@NonNull final CompositeSpacialModel parent, @NonNull final ISpacialModel child) {
        if (parent == null
            || child == null
            || parent.getChildrenCollection().getById(child.getGuid()) != null
            || !ApplicationFramework.getInstance().getChecker().canContainTheChild(parent.getClass(), child.getClass())) return false;

        child.setParent(parent);

        if(!parent.getChildrenCollection().add(child)) return false;

        notifyAddCrudSubscriber(parent, child);

        return true;
    }

    public boolean exists(@NonNull final ArrayList<String> path) { return get(path) != null; }

    public ISpacialModel get(@NonNull final ArrayList<String> path) {
        switch (path.size()){
            case 0: return null;
            case 1: return projectExplorer;
            case 2: return projectExplorer.getChild(path.get(1));
        }

        CompositeSpacialModel model = projectExplorer;

        var excludeFirstAndLastPath = path.subList(1, path.size() - 1);
        for(String curPath : excludeFirstAndLastPath){
            var child = model.getChild(curPath);
            if(child instanceof CompositeSpacialModel cmp) model = cmp;
            else return null;
        }

        return model.getChild(path.getLast());
    }

    public boolean remove(@NonNull ISpacialModel child) {
        var father = child.getParent();
        if(father == null) return false;

        father.getChildrenCollection().remove(child);
        notifyDeleteCrudSubscriber(child);

        return true;
    }

    public boolean rename(@NonNull ISpacialModel model, @NonNull String newName) {
        var parent = model.getParent();
        if(parent == null) return false;

        if(parent.getChildrenCollection()
                .find(m -> m.getName().equals(newName))
                .findFirst()
                .isPresent()) return false;

        model.setName(newName);

        notifyUpdateCrudSubscribers(model);
        return true;
    }

    public boolean updateModel(@NonNull ISpacialModel model, @NonNull Consumer<ISpacialModel> fieldsSetter) {
        fieldsSetter.accept(model);
        notifyUpdateCrudSubscribers(model);
        return true;
    }

    @Override
    public void addSubscriber(ICrudSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ICrudSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifyAddCrudSubscriber(ISpacialModel parent, ISpacialModel child) {
        subscribers.forEach(sub -> sub.updateAddCrud(parent, child));
    }

    @Override
    public void notifyDeleteCrudSubscriber(ISpacialModel child) {
        subscribers.forEach(sub -> sub.updateDeleteCrud(child));
    }

    @Override
    public void notifyUpdateCrudSubscribers(ISpacialModel model) {
        subscribers.forEach(sub -> sub.updateUpdateCrud(model));
    }
}
