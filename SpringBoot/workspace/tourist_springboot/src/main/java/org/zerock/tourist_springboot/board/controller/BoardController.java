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
import org.zerock.tourist_springboot.common.dto.PageResponseDTO;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<BoardDTO> responseDTO = boardService.findList(pageRequestDTO);

        int totalCount = responseDTO.getTotal();
        int page = pageRequestDTO.getPage();
        int size = pageRequestDTO.getSize();

        int displayStartNum = totalCount - ((page - 1) * size);

        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("displayStartNum", displayStartNum);
        return "board/list";
    }



    @GetMapping("/read")
    public String read(@RequestParam("num") Long num,
                       @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
                       Model model) {

        BoardDTO boardDTO = boardService.read(num);
        model.addAttribute("board", boardDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);
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
        BoardDTO dto = boardService.read(num);
        dto.setContent(dto.getContent().replaceAll("<br\\s*/?>", "\n"));
        model.addAttribute("boardDTO", dto);
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
