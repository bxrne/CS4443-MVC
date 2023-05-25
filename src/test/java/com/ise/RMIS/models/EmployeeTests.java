package com.ise.RMIS.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EmployeeTests {
    @Test
    public void testConstructorWithWeekHoursUnder40() {
        int id = 1;
        String name = "John Doe";
        double weekHours = 35;

        Employee employee = new Employee(id, name, weekHours);

        assertEquals(id, employee.getId());
        assertEquals(name, employee.getName());
        assertEquals(weekHours, employee.getWeekHours(), 0.01);
        assertEquals(weekHours * 10, employee.getSalary(), 0.01);
    }

    @Test
    public void testConstructorWithWeekHoursOver40() {
        int id = 2;
        String name = "Jane Smith";
        double weekHours = 45;

        Employee employee = new Employee(id, name, weekHours);

        assertEquals(id, employee.getId());
        assertEquals(name, employee.getName());
        assertEquals(weekHours, employee.getWeekHours(), 0.01);
        assertEquals(400 + (weekHours - 40) * 15, employee.getSalary(), 0.01);
    }

    @Test
    public void testToString() {
        int id = 3;
        String name = "Bob Johnson";
        double weekHours = 38;

        Employee employee = new Employee(id, name, weekHours);

        String expected = id + "," + name + "," + weekHours + "," + weekHours * 10;
        assertEquals(expected, employee.toString());
    }

}
