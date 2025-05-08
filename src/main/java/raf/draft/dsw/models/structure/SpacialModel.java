package raf.draft.dsw.models.structure;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import raf.draft.dsw.models.Building;
import raf.draft.dsw.models.Project;
import raf.draft.dsw.models.ProjectExplorer;
import raf.draft.dsw.models.positional.PositionalElement;
import raf.draft.dsw.utils.StringUtils;

import java.awt.*;
import java.awt.geom.Point2D;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.UUID;

@Getter
@Setter
public class SpacialModel implements ISpacialModel {
    String name;

    @JsonBackReference
    CompositeSpacialModel parent;

    final UUID guid;

    @JsonIgnore // ???
    Color color;

    public SpacialModel(@NonNull String name, CompositeSpacialModel parent){
        StringUtils.requireNonNullAndNotEmpty(name, "name can't be null nor empty!");

        setColor();

        this.guid = UUID.randomUUID();
        this.name = name;
        this.parent = parent;
    }

    private void setColor() {
        Random rand = new Random();
        this.color = new Color(
                rand.nextFloat(),
                rand.nextFloat(),
                rand.nextFloat());
    }

    @Override
    public String toString(){ return name; }

    @Override
    public boolean equals(Object obj) { return obj instanceof ISpacialModel spacialModel && this.getGuid().equals(spacialModel.getGuid()); }

    public SpacialModel(@NonNull ISpacialModel spacialModel) {
        this.guid = UUID.randomUUID();

        this.color = new Color(
                spacialModel.getColor().getRed(),
                spacialModel.getColor().getGreen(),
                spacialModel.getColor().getBlue());

        this.name = spacialModel.getName();
        this.parent = spacialModel.getParent();
    }
}
