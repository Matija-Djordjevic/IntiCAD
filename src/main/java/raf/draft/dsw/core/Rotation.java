package raf.draft.dsw.core;

import lombok.Getter;

import java.awt.*;

public class Rotation {
    @Getter
    int degrees = 0;

    public Rotation(int degrees) {
        setDegrees(degrees);
    }

    // copy const
    public Rotation(Rotation other) {
        if(other == null) {
            setDegrees(0);
            return;
        }
        setDegrees(other.getDegrees());
    }

    public void setDegrees(int newDegrees){
        degrees = newDegrees <= 0
                ? 360 - Math.abs(newDegrees) % 360
                : newDegrees % 360;
    }

    public Rotation() {}
}
