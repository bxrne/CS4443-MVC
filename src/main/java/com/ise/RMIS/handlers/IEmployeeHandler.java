package com.ise.RMIS.handlers;

import com.ise.RMIS.models.Employee;

public interface IEmployeeHandler {

    public void addEmployee(Employee employee);

    public Employee getEmployee(int id);

    public Employee[] getAllEmployees();

}
