package org.zerock.springex2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.springex2.dto.TodoDTO;
import org.zerock.springex2.service.TodoService;

import java.util.List;

@Controller
@Log4j2
// 클래스에 작성한 RequestMapping은 메서드를 부를 주소를 작성기전 적어야하는 주소
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    // 주소 : http://localhost:8081/todo/list
    @GetMapping("/list")
    public String list(Model model) {
        log.info("list");
        // 서비스를 실행하여 tbl_board의 전체 데이터를 저장
//        List<TodoDTO> dtoList = todoService.getAll();
        // 화면에서 사용하기 위해 dtoList라는 이름으로 데이터를 저장
//        model.addAttribute("dtoList",dtoList);
        // 위의 model 데이터 설정을 축약
        model.addAttribute("dtoList",todoService.getAll());
        return "todo/list";
    }
    @GetMapping("/read")
    // Spring의 파리미터를 자동으로 받아 tno저장
    public String read(Long tno, Model model){
        model.addAttribute("dto", todoService.getOne(tno));
        return "todo/read";
    }

    // 주소 : http://localhost:8081/todo/register
    @GetMapping("/register")
    public String register(){
        return "todo/register";
    }
    @PostMapping("/register")
    public String register(TodoDTO todoDTO){
        log.info("register post");
        log.info(todoDTO);
        // service의 register메서드를 실행
        todoService.register(todoDTO);
        return "redirect:/todo/list";
    }
    // GetMapping과 같은 방식
    @RequestMapping(value="/edit", method= RequestMethod.GET)
    public String edit(){
        log.info("edit");
        return "edit";
    }
    // PostMapping과 같은 방식
    @RequestMapping(value="/edit", method= RequestMethod.POST)
    public String editPost(){
        log.info("editPost");
        return "redirect:/hello";
    }
}
