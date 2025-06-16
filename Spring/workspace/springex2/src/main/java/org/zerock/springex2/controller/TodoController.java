package org.zerock.springex2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex2.dto.TodoDTO;
import org.zerock.springex2.service.TodoService;

import javax.servlet.http.HttpSession;
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
    // 하나의 메서드에 여러개의 주소를 설정하는 방식
    // {첫번째 주소, 두번째 주소 , 세번째 주소}
    @GetMapping({"/read", "/edit"})
    // Spring의 파리미터를 자동으로 받아 tno저장
    public void read(Long tno, Model model){
        model.addAttribute("dto", todoService.getOne(tno));
        // return 타입을 void로 설정 할 경우
        // 주소를 기준으로 폴더와 파일을 찾아서 화면(JSP파일)을 출력함
        // todo/read => todo폴더 안의 read.jsp를 실행
        // todo/edit => todo폴더 안의 edit.jsp를 실행
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
    // PostMapping과 같은 방식
    @RequestMapping(value="/edit", method= RequestMethod.POST)
    public String editPost(TodoDTO todoDTO,
                           RedirectAttributes redirectAttributes){
        log.info("editPost");
        if(todoDTO.getTitle().length()>0 && todoDTO.getDueDate() != null){
            String result = todoService.editTodo(todoDTO);
            redirectAttributes.addFlashAttribute("msg", result);
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/read";
        }else{
            redirectAttributes.addFlashAttribute("msg", "수정에 실패했습니다.");
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/edit";
        }
    }
    @PostMapping("/remove")
    public String remove(Long tno){
        // session에 저장된 로그인 유저의 ID를 확인
        //String userId = session.getAttribute("UserId").toString();
        TodoDTO dto = todoService.getOne(tno);
        if(dto != null){
            todoService.removeTodo(tno);
            return "redirect:/todo/list";
        }else{
            return "redirect:/todo/list?errorMsg=error";
        }
    }
}
