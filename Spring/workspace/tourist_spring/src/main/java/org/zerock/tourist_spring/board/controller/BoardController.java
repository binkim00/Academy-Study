package org.zerock.tourist_spring.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.zerock.tourist_spring.board.dto.BoardDTO;
import org.zerock.tourist_spring.board.vo.BoardVO;
import org.zerock.tourist_spring.board.service.BoardService;
import org.zerock.tourist_spring.member.dto.MemberDTO;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false) String searchWord,
                       Model model) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("searchWord", searchWord);
        paramMap.put("limit", 10);
        paramMap.put("offset", 0);

        List<BoardDTO> list = boardService.getPagedList(paramMap);
        int totalCount = boardService.getTotalCount(searchWord);

        model.addAttribute("list", list);
        model.addAttribute("totalCount", totalCount);
        return "list"; // list.jsp
    }

    @GetMapping("/view")
    public String view(@RequestParam("num") int num, Model model) {
        boardService.increaseVisitCount(num);
        BoardDTO board = boardService.getView(num);
        model.addAttribute("board", board);
        return "view"; // view.jsp
    }

    @GetMapping("/write")
    public String writeForm() {
        return "write";
    }

    @PostMapping("/write")
    public String write(BoardDTO dto, HttpSession session) {
        String loginId = ((MemberDTO) session.getAttribute("loginUser")).getId();
        dto.setId(loginId);
        boardService.write(dto);
        return "redirect:/list";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam("num") int num, Model model) {
        BoardDTO board = boardService.getView(num);
        model.addAttribute("board", board);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(BoardDTO dto) {
        boardService.edit(dto);
        return "redirect:/view?num=" + dto.getNum();
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("num") int num) {
        boardService.remove(num);
        return "redirect:/list";
    }
}
