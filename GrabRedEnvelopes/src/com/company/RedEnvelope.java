package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RedEnvelope extends JButton {


    public static List<Position> getPositions() {
        return positions;
    }

    private static List<Position> positions=new ArrayList<>();
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private int x;
    private int y;

    RedEnvelope(){
        setText("红包");
        Random random=new Random();
        x = (random.nextInt(7));
        y=  (random.nextInt(7));
        Position current=new Position(x,y);
        if (positions.size()==0){
            positions.add(new Position(x,y));
        }else {
            for (Position p : positions) {
                current=new Position(x,y);
                if (p.equals(current)){
                    x = (random.nextInt(7));
                    y=  (random.nextInt(7));
                }
            }
            positions.add(current);
        }
    }



}
