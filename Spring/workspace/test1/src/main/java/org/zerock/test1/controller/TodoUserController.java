package org.zerock.test1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.test1.dto.MemberDTO;
import org.zerock.test1.dto.PageRequestDTO;
import org.zerock.test1.dto.TodoDTO;
import org.zerock.test1.service.MemberService;
import org.zerock.test1.service.TodoService;
import org.zerock.test1.vo.MemberVO;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TodoUserController {

    private final MemberService memberService;
    private final TodoService todoService;

    @GetMapping("/member/join")
    public String showJoinForm() {
        return "member/join";
    }

    @PostMapping("/member/join")
    public String registerMember(MemberDTO dto) {
        memberService.register(dto);
        return "redirect:/member/login";
    }

    @GetMapping("/member/login")
    public String showLoginForm() {
        return "member/login";
    }

    @PostMapping("/member/login")
    public String login(@RequestParam("id") String id,
                        @RequestParam("pw") String pw,
                        HttpSession session,
                        RedirectAttributes rttr) {
        MemberVO vo = memberService.login(id, pw);
        if (vo != null) {
            session.setAttribute("loginUser", vo);
            return "redirect:/";
        } else {
            rttr.addFlashAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "redirect:/member/login";
        }
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/member/delete")
    public String showRemoveForm() {
        return "member/delete";
    }

    @PostMapping("/member/delete")
    public String delete(HttpSession session) {
        MemberVO vo = (MemberVO) session.getAttribute("loginUser");
        if (vo != null) {
            memberService.remove(vo.getId());
            session.invalidate();
        }
        return "redirect:/";
    }

    @GetMapping("/todo/list")
    public String list(@Valid PageRequestDTO pageRequestDTO,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        log.info("할일 목록 조회 요청: {}", pageRequestDTO);
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
        return "todo/list";
    }

    @GetMapping({"/todo/read", "/todo/edit"})
    public void read(Long tno, PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("dto", todoService.getOne(tno));
    }

    @GetMapping("/todo/register")
    public String register() {
        return "todo/register";
    }

    @PostMapping("/todo/register")
    public String register(TodoDTO dto, HttpSession session) {
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        if (loginUser == null) {
            return "redirect:/member/login";
        }

        dto.setWriter(loginUser.getId());
        todoService.register(dto);
        return "redirect:/todo/list";
    }



    @PostMapping("/todo/edit")
    public String editPost(TodoDTO todoDTO,
                           RedirectAttributes redirectAttributes,
                           PageRequestDTO pageRequestDTO,
                           HttpSession session) {

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        TodoDTO origin = todoService.getOne(todoDTO.getTno());
        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        if (origin != null && loginUser != null && origin.getWriter().equals(loginUser.getId())) {
            todoDTO.setWriter(loginUser.getId());

            if (todoDTO.getTitle() != null && !todoDTO.getTitle().trim().isEmpty() && todoDTO.getDueDate() != null) {
                String result = todoService.modify(todoDTO);
                redirectAttributes.addFlashAttribute("msg", result);
                redirectAttributes.addAttribute("tno", todoDTO.getTno());
                return "redirect:/todo/read";
            } else {
                redirectAttributes.addFlashAttribute("msg", "수정에 실패했습니다.");
            }
        } else {
            redirectAttributes.addFlashAttribute("msg", "수정 권한이 없습니다.");
        }


        redirectAttributes.addAttribute("tno", todoDTO.getTno());
        return "redirect:/todo/edit";
    }



    @PostMapping("/todo/remove")
    public String remove(Long tno,
                         PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes,
                         HttpSession session) {

        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        TodoDTO dto = todoService.getOne(tno);

        MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

        if (dto != null && loginUser != null && dto.getWriter().equals(loginUser.getId())) {
            todoService.remove(tno, loginUser.getId());
        } else {
            redirectAttributes.addFlashAttribute("msg", "삭제 권한이 없습니다.");
        }

        return "redirect:/todo/list";
    }

}
