package com.ise.RMIS.lib;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class Model {
    private File file;

    public Model() throws FileNotFoundException {
        file = new File("src/main/resources/static/database.csv");

        if (!file.exists()) {
            throw new FileNotFoundException("Database file not found: " + file.getAbsolutePath());
        }
    }

    public void addEmployee(Employee employee) {
        if (getEmployee(employee.getId()) != null) {
            return;
        }

        try (FileWriter writer = new FileWriter(file, true);) {
            writer.write(employee.toString() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        if (getEmployee(id) != null) {
            return;
        }

        // TODO :: Remove line with id from file
    }

    public void updateEmployee(Employee employee) {
        // TODO :: Check if employee exists (by id)
        // TODO :: Update employee
    }

    public Employee getEmployee(int id) {
        try (FileReader reader = new FileReader(file)) {
            int c;
            String data = "";
            while ((c = reader.read()) != -1) {
                data += (char) c;
            }

            String[] lines = data.split("\n");

            for (String line : lines) {
                String[] employeeData = line.split(",");
                if (Integer.parseInt(employeeData[0]) == id) {
                    return new Employee(Integer.parseInt(employeeData[0]), employeeData[1],
                            Double.parseDouble(employeeData[2]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Employee[] getAllEmployees() {
        try (FileReader reader = new FileReader(file);) {
            int c;
            String data = "";
            while ((c = reader.read()) != -1) {
                data += (char) c;
            }

            String[] lines = data.split("\n");
            Employee[] employees = new Employee[lines.length];

            for (int i = 0; i < lines.length; i++) {
                String[] employeeData = lines[i].split(",");
                employees[i] = new Employee(Integer.parseInt(employeeData[0]), employeeData[1],
                        Double.parseDouble(employeeData[2]));
            }

            return employees;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Employee[0];
    }

}
