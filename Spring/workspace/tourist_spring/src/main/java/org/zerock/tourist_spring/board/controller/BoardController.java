package org.zerock.tourist_spring.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.tourist_spring.board.dto.BoardDTO;
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
    public String list(@RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(required = false) String searchWord,
                       Model model) {
        int limit = 10;
        int offset = (pageNum - 1) * limit;

        String likeSearch = (searchWord != null && !searchWord.isEmpty())
                ? "%" + searchWord + "%"
                : null;

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("searchWord", likeSearch);
        paramMap.put("limit", limit);
        paramMap.put("offset", offset);

        List<BoardDTO> list = boardService.getPagedList(paramMap);
        int totalCount = boardService.getTotalCount(likeSearch);

        int totalPage = (int) Math.ceil((double) totalCount / limit);

        model.addAttribute("list", list);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("pageNum", pageNum);
        model.addAttribute("searchWord", searchWord);

        return "list";
    }



    @GetMapping("/view")
    public String view(@RequestParam("num") int num, Model model) {
        boardService.increaseVisitCount(num);
        BoardDTO board = boardService.getView(num);
        model.addAttribute("board", board);
        return "view";
    }

    @GetMapping("/write")
    public String writeForm(HttpSession session) {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/login";
        }
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
    public String editForm(@RequestParam("num") int num, Model model, HttpSession session, RedirectAttributes rttr) {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            rttr.addFlashAttribute("msg", "로그인 후 이용해주세요.");
            return "redirect:/login";
        }

        BoardDTO board = boardService.getView(num);

        board.setContent(board.getContent().replace("<br>", "\n").replace("<br/>", "\n"));

        if (!loginUser.getId().equals(board.getId())) {
            rttr.addFlashAttribute("msg", "작성자만 수정할 수 있습니다.");
            rttr.addAttribute("num", num);
            return "redirect:/view";
        }

        model.addAttribute("board", board);
        return "edit";
    }



    @PostMapping("/edit")
    public String edit(BoardDTO dto, HttpSession session, RedirectAttributes rttr) {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            rttr.addFlashAttribute("msg", "로그인 후 이용해주세요.");
            return "redirect:/login";
        }

        String loginId = loginUser.getId();

        if (dto.getTitle() == null || dto.getTitle().isBlank()) {
            rttr.addFlashAttribute("msg", "제목은 필수입니다.");
            rttr.addAttribute("num", dto.getNum());
            return "redirect:/edit?num=" + dto.getNum();
        }

        BoardDTO original = boardService.getView(dto.getNum());

        if (!loginId.equals(original.getId())) {
            rttr.addFlashAttribute("msg", "작성자만 수정할 수 있습니다.");
            rttr.addAttribute("num", dto.getNum());
            return "redirect:/view";
        }

        boardService.edit(dto);
        rttr.addFlashAttribute("msg", "수정이 완료되었습니다.");
        rttr.addAttribute("num", dto.getNum());
        return "redirect:/view";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("num") int num, HttpSession session, RedirectAttributes rttr) {
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            rttr.addFlashAttribute("msg", "로그인 후 이용해주세요.");
            return "redirect:/login";
        }
        String loginId = loginUser.getId();

        BoardDTO board = boardService.getView(num);
        if (board == null) {
            rttr.addFlashAttribute("msg", "존재하지 않는 게시글입니다.");
            return "redirect:/list";
        }

        if (!loginId.equals(board.getId())) {
            rttr.addFlashAttribute("msg", "작성자만 삭제할 수 있습니다.");
            rttr.addAttribute("num", num);
            return "redirect:/view";
        }

        boardService.remove(num);
        rttr.addFlashAttribute("msg", "삭제되었습니다.");
        return "redirect:/list";
    }


}
