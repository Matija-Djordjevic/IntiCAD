package raf.draft.dsw.utils;

import lombok.NonNull;
import raf.draft.dsw.models.structure.SpacialModel;

import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class PathUtils {
    private PathUtils(){}

    public static ArrayList<String> toStringList(@NonNull TreePath path) { return new ArrayList<>(Arrays.asList((String[]) path.getPath())); }

    public static TreePath toTreePath(@NonNull ArrayList<String> path) { return new TreePath(path.toArray()); }

    private final static String pathComponentsSeparator = " / ";

    public static String toSingleStr(@NonNull ArrayList<String> path) { return String.join(pathComponentsSeparator, path); }

    public static String toSingleStr(@NonNull TreePath path) { return String.join(pathComponentsSeparator, toStringList(path)); }

    public static ArrayList<String> getPath(SpacialModel model){
        var path = new ArrayList<String>();
        path.add(model.getName());

        var parent = model.getParent();
        while(parent != null){
            path.add(parent.getName());
            parent = parent.getParent();
        }

        Collections.reverse(path);
        return path;
    }

    public static String getPathAsStr(SpacialModel model) { return toSingleStr(getPath(model));}
}
