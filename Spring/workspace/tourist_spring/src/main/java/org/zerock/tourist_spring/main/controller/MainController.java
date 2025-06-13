package org.zerock.tourist_spring.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index"; // /WEB-INF/views/index.jsp
    }
}
