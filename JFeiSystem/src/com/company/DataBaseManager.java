package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataBaseManager {
    Connection connection = null;
    private Statement statement;

    public DataBaseManager() {

        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getException());
            e.printStackTrace();
        }
        try {
            Properties prop = new Properties();
            prop.put("charSet", "gb2312");
            connection = DriverManager.getConnection("jdbc:odbc:WuLiuGuanLi", prop);
            System.out.println("连接成功!");
            statement = connection.createStatement();
            // 创建SQL语句对象
            String creat = "create table student(id char(10) primary key,name char(15),Usage_time integer,balance integer)";
            //int result = statement.executeUpdate(creat);
            String insert1="insert into student values('01','张三',3,20)";
            String insert2="insert into student values('02','李四',5,55)";
            String insert3="insert into student values('03','王五',2,20)";
            String insert4="insert into student values('04','赵六',6,98)";
            /*statement.executeUpdate(insert1);
            statement.executeUpdate(insert2);
            statement.executeUpdate(insert3);
            statement.executeUpdate(insert4);*/


        } catch (SQLException e) {
            System.out.println(e.getNextException());
            e.printStackTrace();
        }
    }

    public List<Student> que() {
        int result=0;
        List<Student> students=new ArrayList();
        students.clear();
        try {
            String sql = "select * from student";
            //System.out.println(dingDan.getWuLiuGongSi()+dingDan.getPhone()+":"+dingDan.getID());
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                /*System.out.println(resultSet.getString("id")+
                        ":"+resultSet.getString("WuLiuGongSi")+":"
                        +resultSet.getString("ShouJianRen")+":"
                        +resultSet.getString("Phone")+":");*/
                Student getStu=new Student(resultSet.getString("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("Usage_time"),
                        resultSet.getInt("balance"));

                students.add(getStu);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

}
