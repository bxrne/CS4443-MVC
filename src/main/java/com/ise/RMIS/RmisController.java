package com.ise.RMIS;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RmisController {

    @GetMapping("/")
    public String index() {
        return "index"; // Returns the name of the HTML file without the extension
    }
}
