package org.zerock.tourist_spring.member.controller;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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



    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String id,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes rttr) {

        MemberDTO dto = memberService.login(id, password);
        if (dto != null) {
            session.setAttribute("loginUser", dto);
            return "redirect:/index";
        } else {
            rttr.addFlashAttribute("msg",
                    "아이디 또는 비밀번호가 틀렸습니다.");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
