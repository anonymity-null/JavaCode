package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MyFrame extends JFrame {
    private final Vector rowData;
    private final DefaultTableModel model;
    private final JTable jTable;
    private final JScrollPane comp;
    MyFrame() {
        DataBaseManager dataBaseManager=new DataBaseManager();

        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Vector name = new Vector();
        name.add("id");
        name.add("name");
        name.add("Usage-time");
        name.add("balance");
        Vector data = new Vector();
        rowData = new Vector();
        data.add(rowData);
        model=new DefaultTableModel();
        jTable=new JTable();
        model.setDataVector(data, name);
        jTable.setModel(model);
        comp=new JScrollPane(jTable);
        List<Student> students = dataBaseManager.que();
        showTable(students);

        add(comp);

        setVisible(true);

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getUsage_time()*10>s.getBalance()){
                JOptionPane.showMessageDialog
                        (null,
                                s.getName()+"费用不足，请及时缴费",
                                "提示",
                                JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }
    void showTable(List<Student> students) {

        model.setRowCount(0);

        List<Vector> vectorList=new ArrayList<Vector>();
        Vector vector;

        for (int i = 0; i <students.size(); i++) {

            System.out.println(students.get(i).getBalance());

            vector=new Vector();

            vector.add(students.get(i).getId());
            vector.add(students.get(i).getName());
            vector.add(students.get(i).getUsage_time());
            vector.add(students.get(i).getBalance());

            vectorList.add(vector);


        }
        for (int i = 0; i <vectorList.size(); i++) {
            model.addRow(vectorList.get(i));
        }
    }
}
