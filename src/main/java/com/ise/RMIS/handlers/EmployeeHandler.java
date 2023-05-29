package com.ise.RMIS.handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.ise.RMIS.models.Employee;

public class EmployeeHandler implements IEmployeeHandler {
    private File file;

    public EmployeeHandler() throws FileNotFoundException {
        file = new File("src/main/resources/static/database.csv");

        if (!file.exists()) {
            throw new FileNotFoundException("Database file not found: " + file.getAbsolutePath());
        }
    }

    /*
     * Constructor that takes a path to the database file
     * Specifically for database mocking in tests
     */
    public EmployeeHandler(String path) throws FileNotFoundException {
        file = new File(path);

        if (!file.exists()) {
            throw new FileNotFoundException("Database file not found: " + file.getAbsolutePath());
        }
    }

    /*
     * Adds an employee to the database if they don't already exist
     */
    public void addEmployee(Employee employee) {
        if (getEmployee(employee.getId()) != null) {
            return;
        }

        int unusedId = findUnusedId();

        // Update the employee object with the unused ID
        employee.setId(unusedId);

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(employee.toString() + "\n");
        } catch (IOException e) {
        }
    }

    private int findUnusedId() {
        int unusedId = 1;
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0].trim());
                if (id >= unusedId) {
                    unusedId = id + 1;
                }
            }
        } catch (IOException e) {
            // e.printStackTrace();
        }
        return unusedId;
    }

    /*
     * Retrieves an employee from the database by their ID
     */
    public Employee getEmployee(int id) {
        try (FileReader reader = new FileReader(file)) {
            int c;
            StringBuilder dataBuilder = new StringBuilder();
            while ((c = reader.read()) != -1) {
                dataBuilder.append((char) c);
            }
            String data = dataBuilder.toString();

            String[] lines = data.split("\n");

            for (String line : lines) {
                String[] employeeData = line.split(",");
                if (employeeData.length == 4 && Integer.parseInt(employeeData[0]) == id) {
                    return new Employee(
                            Integer.parseInt(employeeData[0]),
                            employeeData[1],
                            Double.parseDouble(employeeData[2]),
                            Double.parseDouble(employeeData[3]));
                }

            }

        } catch (Exception e) {
            // e.printStackTrace();
        }

        return null;
    }

    /*
     * Retrieves all employees from the database
     */
    public Employee[] getAllEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<Employee> employeeList = new ArrayList<>();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] employeeData = line.split(",");
                Employee employee = new Employee(
                        Integer.parseInt(employeeData[0]),
                        employeeData[1],
                        Double.parseDouble(employeeData[2]));
                employeeList.add(employee);
            }

            Collections.reverse(employeeList);

            return employeeList.toArray(new Employee[0]);
        } catch (IOException e) {
            // e.printStackTrace();
        }

        return new Employee[0];
    }

}
