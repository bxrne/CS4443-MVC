package com.ise.RMIS;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ise.RMIS.handlers.AuthHandler;
import com.ise.RMIS.handlers.SalaryResponse;


@Controller
public class RmisController {

    @GetMapping("/")
    public String index() {
        return "index"; // Returns the name of the HTML file without the extension
    }

    @PostMapping("/calc")
    public @ResponseBody SalaryResponse calculateSalary(@RequestParam("hours") String hours) {
        int hoursWorked = Integer.parseInt(hours);

        // Here we would check the database if we were to take in a employee id we could see their salary and calculate based off that by dividing salary_wk by hours_wk
        double salary = hoursWorked * 10.0;

        SalaryResponse salaryResponse = new SalaryResponse();
        salaryResponse.setSalary(salary);

        return salaryResponse;
    }

    
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam("username") String username, @RequestParam("password") String password, Model model){
        try{
            System.out.println(password);
            AuthHandler auhandler = new AuthHandler(username, password);
            return "redirect:/";
        }
        catch (Exception e){
            model.addAttribute("errorMessage", "Username or password is incorrect");
            return "login";
        }
    }

    // @GetMapping("/login#")
    // public String forgotPwd(Model model){
    //     model.addAttribute("errorMessage", "This would redirect to an email or phone 2FA recovery");
    //     return "login";
    // }

}




