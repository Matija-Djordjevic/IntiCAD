package raf.draft.dsw.models.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.awt.*;
import java.util.UUID;

public interface ISpacialModel {
    // Getters
    String getName();

    CompositeSpacialModel getParent();
    UUID getGuid();
    Color getColor();

    // Setters
    void setName(String name);
    void setParent(CompositeSpacialModel parent);
    void setColor(Color color);

    // Other common methods
    @Override
    String toString();

    @Override
    boolean equals(Object obj);
}