package com.ise.RMIS;

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
    public @ResponseBody Employee calculateSalary(@RequestParam("hours") String hours) {
        int hoursWorked = Integer.parseInt(hours);
        int salary;
        int id = 1;

        try {
            EmployeeHandler employeeHandler = new EmployeeHandler();

            if (hoursWorked <= 40) {
                salary = hoursWorked * 10;
            } else {
                salary = 400 + (hoursWorked - 40) * 15;
            }

            Employee emp = new Employee(id, "John Doe", hoursWorked, salary); // ADD NAME
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

    @PostMapping("/login")
    public String loginSubmit(@RequestParam("username") String username, @RequestParam("password") String password,
            Model model) {
        try {
            AuthHandler auhandler = new AuthHandler(username, password);

            // return admin page

            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", "Username or password is incorrect");
            return "login";
        }
    }

    // @GetMapping("/login#")
    // public String forgotPwd(Model model){
    // model.addAttribute("errorMessage", "This would redirect to an email or phone
    // 2FA recovery");
    // return "login";
    // }

}
