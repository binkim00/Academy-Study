package org.zerock.tourist_springboot.program.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.zerock.tourist_springboot.program.service.ProgramService;

@Controller
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    @GetMapping("/program")
    public String listPrograms(Model model) {
        model.addAttribute("programList", programService.getAllPrograms());
        return "program/program";
    }
}

