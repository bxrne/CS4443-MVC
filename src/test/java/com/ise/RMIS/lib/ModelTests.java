package com.ise.RMIS.lib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ise.RMIS.handlers.Model;
import com.ise.RMIS.models.Employee;

class ModelTests {
    private static final String TEST_DATABASE_PATH = "src/main/resources/static/test-database.csv";
    private Model model;

    @BeforeEach
    void setUp() throws IOException {
        // Create a test database file
        File testFile = new File(TEST_DATABASE_PATH);

        if (testFile.exists()) {
            testFile.delete();
        }

        testFile.createNewFile();

        model = new Model(TEST_DATABASE_PATH);
    }

    @Test
    void testModelConstructor_ThrowsFileNotFoundException() {
        assertThrows(FileNotFoundException.class, () -> new Model("nonexistent-file.csv"));
    }

    @Test
    void testAddEmployee() {
        Employee employee = new Employee(1, "John Doe", 1000.0);

        // Add the employee to the model
        model.addEmployee(employee);

        // Retrieve the added employee
        Employee retrievedEmployee = model.getEmployee(1);

        // Assert that the retrieved employee is not null
        assertNotNull(retrievedEmployee);
        // Assert that the retrieved employee has the same ID as the added employee
        assertEquals(employee.getId(), retrievedEmployee.getId());
        // Assert that the retrieved employee has the same name as the added employee
        assertEquals(employee.getName(), retrievedEmployee.getName());
        // Assert that the retrieved employee has the same salary as the added employee
        assertEquals(employee.getSalary(), retrievedEmployee.getSalary(), 0.0);
    }

    @Test
    void testGetEmployee_ExistingEmployee() {
        Employee employee = new Employee(1, "John Doe", 1000.0);

        // Add the employee to the model
        model.addEmployee(employee);

        // Retrieve the existing employee
        Employee retrievedEmployee = model.getEmployee(1);

        // Assert that the retrieved employee is not null
        assertNotNull(retrievedEmployee);
        // Assert that the retrieved employee has the same ID as the added employee
        assertEquals(employee.getId(), retrievedEmployee.getId());
        // Assert that the retrieved employee has the same name as the added employee
        assertEquals(employee.getName(), retrievedEmployee.getName());
        // Assert that the retrieved employee has the same salary as the added employee
        assertEquals(employee.getSalary(), retrievedEmployee.getSalary(), 0.0);
    }

    @Test
    void testGetEmployee_NonexistentEmployee() {
        // Retrieve a non-existent employee
        Employee retrievedEmployee = model.getEmployee(1);

        // Assert that the retrieved employee is null
        assertNull(retrievedEmployee);
    }

    @Test
    void testGetAllEmployees() {
        Employee[] employees = {
                new Employee(1, "John Doe", 1000.0),
                new Employee(2, "Jane Smith", 2000.0),
                new Employee(3, "Bob Johnson", 3000.0)
        };

        // Add employees to the model
        for (Employee employee : employees) {
            model.addEmployee(employee);
        }

        // Retrieve all employees
        Employee[] retrievedEmployees = model.getAllEmployees();

        // Assert that the retrieved employees array has the same length as the added
        // employees array
        assertEquals(employees.length, retrievedEmployees.length);

        // Assert that each retrieved employee matches the corresponding added employee
        for (int i = 0; i < employees.length; i++) {
            assertEquals(employees[i].getId(), retrievedEmployees[i].getId());
            assertEquals(employees[i].getName(), retrievedEmployees[i].getName());
            assertEquals(employees[i].getSalary(), retrievedEmployees[i].getSalary(), 0.0);
        }
    }
}
