package com.ise.RMIS.models;

public class Employee {
    private int id;
    private String name;
    private double weekHours;
    public double salary; // Idk how to pass this into index.html without making it public

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

    public Employee(String _name, double _weekHours, double _salary) {
        name = _name;
        weekHours = _weekHours;

        salary = _salary;

    }

    public Employee(int _id, String _name, double _weekHours, double _salary) {
        id = _id;
        name = _name;
        weekHours = _weekHours;
        salary = _salary;

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

    public void setId(int unusedId) {
        id = unusedId;
    }

}
