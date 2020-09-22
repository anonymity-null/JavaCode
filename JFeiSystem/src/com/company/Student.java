package com.company;

public class Student {
    private String id;
    private String name;
    private int Usage_time;
    private int balance;

    public Student(String id, String name, int usage_time, int balance) {
        this.id = id;
        this.name = name;
        this.Usage_time = usage_time;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUsage_time() {
        return Usage_time;
    }

    public void setUsage_time(int usage_time) {
        Usage_time = usage_time;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
