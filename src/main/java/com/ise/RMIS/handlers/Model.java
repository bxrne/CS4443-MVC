package com.ise.RMIS.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import com.ise.RMIS.models.Employee;

public class Model {
    private File file;

    public Model() throws FileNotFoundException {
        file = new File("src/main/resources/static/database.csv");

        if (!file.exists()) {
            throw new FileNotFoundException("Database file not found: " + file.getAbsolutePath());
        }
    }

    /*
     * Constructor that takes a path to the database file
     * Specifically for database mocking in tests
     */
    public Model(String path) throws FileNotFoundException {
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

        try (FileWriter writer = new FileWriter(file, true);) {
            writer.write(employee.toString() + "\n");
        } catch (Exception e) {
            // e.printStackTrace();
        }
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
                if (Integer.parseInt(employeeData[0]) == id) {
                    return new Employee(
                            Integer.parseInt(employeeData[0]),
                            employeeData[1],
                            Double.parseDouble(employeeData[2]));
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
        try (FileReader reader = new FileReader(file);) {
            int c;
            StringBuilder dataBuilder = new StringBuilder();
            while ((c = reader.read()) != -1) {
                dataBuilder.append((char) c);
            }
            String data = dataBuilder.toString();

            String[] lines = data.split("\n");
            Employee[] employees = new Employee[lines.length];

            for (int i = 0; i < lines.length; i++) {
                String[] employeeData = lines[i].split(",");
                employees[i] = new Employee(
                        Integer.parseInt(employeeData[0]),
                        employeeData[1],
                        Double.parseDouble(employeeData[2]));
            }

            return employees;
        } catch (Exception e) {
            // e.printStackTrace();
        }

        return new Employee[0];
    }

}
