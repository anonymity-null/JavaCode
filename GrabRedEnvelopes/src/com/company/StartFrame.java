package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartFrame extends JFrame {
    private JButton button;
    private JLabel label;
    private String labelStr="请输入总金额：";
    private JTextField textField;
    StartFrame(){
        setSize(500,100);
        setLayout(new GridLayout(1,3));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        button=new JButton("确定");
        label=new JLabel(labelStr);
        textField=new JTextField();

        add(label);
        add(textField);
        add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String trim = textField.getText().trim();
                if (!trim.isEmpty()){
                    new MyFrame(Float.parseFloat(trim));
                    setVisible(false);
                }else {
                    textField.setText("请输入总金额！");
                }
            }
        });
        setVisible(true);
    }
}
