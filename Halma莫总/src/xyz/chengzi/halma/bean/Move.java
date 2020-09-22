package xyz.chengzi.halma.bean;

import xyz.chengzi.halma.model.ChessBoardLocation;

import java.awt.*;

public class Move {
    private Color color;
    private ChessBoardLocation oldLocation;
    private ChessBoardLocation location;

    public Move(Color color, ChessBoardLocation oldLocation, ChessBoardLocation location) {
        this.color = color;
        this.oldLocation = oldLocation;
        this.location = location;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ChessBoardLocation getOldLocation() {
        return oldLocation;
    }

    public void setOldLocation(ChessBoardLocation oldLocation) {
        this.oldLocation = oldLocation;
    }

    public ChessBoardLocation getLocation() {
        return location;
    }

    public void setLocation(ChessBoardLocation location) {
        this.location = location;
    }
}
