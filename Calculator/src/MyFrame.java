import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    private JTextField textField1,textField2,textField3;
    private JPanel jPanel1,jPanel2;
    private String[] strings=new String[]{"1","2","3","+","4","5","6","-","7","8","9","*","0","√","C","/"};
    private JButton[] jButtons=new JButton[16];
    private boolean done=false;
    {
        textField1=new JTextField();
        textField1.setSize(500,100);
        textField2=new JTextField();
        textField2.setSize(500,100);
        textField3=new JTextField();
        textField3.setSize(500,100);

        jPanel1=new JPanel();
        jPanel2=new JPanel();

        for (int i = 0; i < 16; i++) {
            jButtons[i]=new JButton(strings[i]);
            int finalI = i;
            jButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (done){

                        textField2.setText(textField2.getText().concat(strings[finalI]));
                    }else textField1.setText(textField1.getText().concat(strings[finalI]));
                }
            });
        }
        jButtons[13]=new JButton("√");
        jButtons[13].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                done=true;
            }
        });
        jButtons[3]=new JButton("+");
        jButtons[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text1 = textField1.getText();
                String text2 = textField2.getText();
                int result=Integer.valueOf(text1)+Integer.valueOf(text2);
                textField3.setText(result+"");
                done=false;
            }
        });
        jButtons[7]=new JButton("-");
        jButtons[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text1 = textField1.getText();
                String text2 = textField2.getText();
                int result=Integer.valueOf(text1)-Integer.valueOf(text2);
                textField3.setText(result+"");
                done=false;
            }
        });
        jButtons[11]=new JButton("*");
        jButtons[11].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text1 = textField1.getText();
                String text2 = textField2.getText();
                int result=Integer.valueOf(text1)*Integer.valueOf(text2);
                textField3.setText(result+"");
                done=false;
            }
        });
        jButtons[15]=new JButton("/");
        jButtons[15].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text1 = textField1.getText();
                String text2 = textField2.getText();
                float result=Float.valueOf(text1)/Float.valueOf(text2);
                textField3.setText(result+"");
                done=false;
            }
        });
        jButtons[14]=new JButton("C");
        jButtons[14].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField3.setText("");
                textField2.setText("");
                textField1.setText("");
                done=false;
            }
        });
    }
    MyFrame(){
        setTitle("领小+简单计算机");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(500,500);

        jPanel1.setLayout(new GridLayout(3,1));
        jPanel2.setLayout(new GridLayout(4,4));

        jPanel1.add(textField1);
        jPanel1.add(textField2);
        jPanel1.add(textField3);

        for (int i = 0; i < 16; i++) {
            jPanel2.add(jButtons[i]);
        }


        setLayout(new BorderLayout());
        add(jPanel1,BorderLayout.NORTH);
        add(jPanel2);

        setVisible(true);
    }
}
