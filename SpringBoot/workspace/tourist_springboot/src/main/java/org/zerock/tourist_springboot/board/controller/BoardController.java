package org.zerock.tourist_springboot.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.tourist_springboot.board.dto.BoardDTO;
import org.zerock.tourist_springboot.board.service.BoardService;
import org.zerock.tourist_springboot.common.dto.PageRequestDTO;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("responseDTO", boardService.findList(pageRequestDTO));
        return "board/list";
    }

    @GetMapping("/read")
    public String read(@RequestParam("num") Long num, Model model) {
        BoardDTO boardDTO = boardService.read(num);
        model.addAttribute("board", boardDTO);
        return "board/read";
    }

    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("boardDTO", new BoardDTO());
        return "board/write";
    }

    @PostMapping("/write")
    public String writeSubmit(@ModelAttribute BoardDTO boardDTO) {
        boardService.write(boardDTO);
        return "redirect:/list";
    }

    @GetMapping("/edit")
    public String editForm(@RequestParam("num") Long num, Model model) {
        BoardDTO boardDTO = boardService.read(num);
        model.addAttribute("boardDTO", boardDTO);
        return "board/edit";
    }

    @PostMapping("/edit")
    public String editSubmit(@ModelAttribute BoardDTO boardDTO) {
        boardService.update(boardDTO);
        return "redirect:/read?num=" + boardDTO.getNum();
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("num") Long num) {
        boardService.remove(num);
        return "redirect:/list";
    }

}
