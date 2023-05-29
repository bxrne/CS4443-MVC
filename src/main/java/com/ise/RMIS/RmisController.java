package com.ise.RMIS;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ise.RMIS.handlers.AuthHandler;
import com.ise.RMIS.handlers.EmployeeHandler;
import com.ise.RMIS.models.Employee;

@Controller
public class RmisController {

    @GetMapping("/")
    public String index() {
        return "index"; // Returns the name of the HTML file without the extension
    }

    @PostMapping("/calc")
    public @ResponseBody Employee calculateSalary(@RequestParam("hours") String hours,
            @RequestParam("name") String name) {
        int hoursWorked = Integer.parseInt(hours);
        int salary;

        try {
            EmployeeHandler employeeHandler = new EmployeeHandler();

            if (hoursWorked <= 40) {
                salary = hoursWorked * 10;
            } else {
                salary = 400 + (hoursWorked - 40) * 15;
            }

            Employee emp = new Employee(name, hoursWorked, salary);
            employeeHandler.addEmployee(emp);

            return emp;

        } catch (Exception e) {
            // panic
        }

        return null;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin(Model model) throws FileNotFoundException {
        EmployeeHandler handler = new EmployeeHandler();

        model.addAttribute("employees", handler.getAllEmployees());

        return "admin";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam("username") String username, @RequestParam("password") String password,
            Model model) {
        try {
            new AuthHandler(username, password);
            return "redirect:/admin";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "Username or password is incorrect");
            return "login";
        }
    }
}
