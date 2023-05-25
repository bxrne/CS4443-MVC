package com.ise.RMIS.models;

public class Employee {
    private int id;
    private String name;
    private double weekHours;
    private double salary;

    public Employee(int _id, String _name, double _weekHours) {
        id = _id;
        name = _name;
        weekHours = _weekHours;

        if (weekHours <= 40) {
            salary = weekHours * 10;
        } else {
            salary = 400 + (weekHours - 40) * 15;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWeekHours() {
        return weekHours;
    }

    public double getSalary() {
        return salary;
    }

    public String toString() {
        return id + "," + name + "," + weekHours + "," + salary;
    }

}
