package com.company;

import javafx.geometry.Pos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyFrame extends JFrame {

    List<RedEnvelope> redEnvelopes=new ArrayList<>();
    List<Float> moneys=new ArrayList<>();
    JButton redEnvelope[][]=new JButton[7][7];

    MyFrame(float total){
        //红包个数
        int count;

        Random random=new Random();
        count = random.nextInt(8)+7;

        for (int i = 0; i < count; i++) {
            redEnvelopes.add(new RedEnvelope());
        }
        List<Position> positions=RedEnvelope.getPositions();


        System.out.println("count="+count);

        float all=total;
        BigDecimal subtract = new BigDecimal(String.format("%.2f",all));

        for (int i = 0; i < count-1; i++) {
            int leftCount=count-i;
            float avg=all/leftCount;
            float money=random.nextFloat()*avg;
            BigDecimal bigDecimal=new BigDecimal(String.format("%.2f",money));
            subtract=subtract.subtract(bigDecimal);
            all=subtract.floatValue();

            //all-=money;
            //sended+=money;
            moneys.add(bigDecimal.floatValue());
            System.out.println(bigDecimal);
            //sended+=money;
        }
        moneys.add(subtract.floatValue());
        System.out.println(subtract);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                redEnvelope[i][j]=new JButton("红包");
                int finalI = i;
                int finalJ = j;
                redEnvelope[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        redEnvelope[finalI][finalJ].setText("empty");
                        redEnvelope[finalI][finalJ].setBackground(Color.GRAY);
                    }
                });
            }
        }

        for (int i=0;i<positions.size();i++) {

            Position p=positions.get(i);
            System.out.println(p);
            int finalI = i;
            redEnvelope[p.x][p.y]=new JButton("红包");
            redEnvelope[p.x][p.y].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println(money.get(finalI)+"元");
                    redEnvelope[p.x][p.y].setText(moneys.get(finalI)+"元");
                    System.out.println(redEnvelope[p.x][p.y].getText());
                    redEnvelope[p.x][p.y].setBackground(Color.RED);
                    System.out.println(p.x+","+p.y);

                }
            });
        }


        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                add(redEnvelope[i][j]);
            }
        }


        setSize(1000,1000);
        setLayout(new GridLayout(7,7));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        setVisible(true);
    }




}
