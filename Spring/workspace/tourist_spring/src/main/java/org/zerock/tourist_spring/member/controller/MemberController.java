package org.zerock.tourist_spring.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.zerock.tourist_spring.member.dto.MemberDTO;
import org.zerock.tourist_spring.member.service.MemberService;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/register")
    public String showRegisterForm() {
        return "join";
    }

    @PostMapping("/member/register")
    public String register(MemberDTO dto) {
        log.info("회원가입 시도: {}", dto);

        memberService.register(dto);
        return "redirect:/";
    }
}
