package org.zerock.springex2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex2.dto.PageRequestDTO;
import org.zerock.springex2.dto.TodoDTO;
import org.zerock.springex2.service.TodoService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@Log4j2
// 클래스에 작성한 RequestMapping은 메서드를 부를 주소를 작성기전 적어야하는 주소
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    // 주소 : http://localhost:8081/todo/list
//    @GetMapping("/list")
//    public String list(Model model) {
//        log.info("list");
//        // 서비스를 실행하여 tbl_board의 전체 데이터를 저장
////        List<TodoDTO> dtoList = todoService.getAll();
//        // 화면에서 사용하기 위해 dtoList라는 이름으로 데이터를 저장
    ////        model.addAttribute("dtoList",dtoList);
//        // 위의 model 데이터 설정을 축약
//        model.addAttribute("dtoList",todoService.getAll());
//        return "todo/list";
//    }
    @GetMapping("/list")
    // @Valid 어노테이션을 설정하여 값이 올바르게 설정되었는지 확인할 수 있음
    // BindingResult 객체에 값이 올바른지 확인할 수 있는 정보가 저장됨
    public String list(@Valid PageRequestDTO pageRequestDTO,
                       BindingResult bindingResult,
                       Model model){
        log.info(pageRequestDTO);
        // 화면에서 받아온 값이 올바르지 않으면 실행
        if(bindingResult.hasErrors()){
            // 올바르지 않은 경우 기본값이 저장된 pageRequestDTO를 생성
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO"
                , todoService.getList(pageRequestDTO));
        return "todo/list";
    }
    // 하나의 메서드에 여러개의 주소를 설정하는 방식
    // {첫번째 주소, 두번째 주소 , 세번째 주소}
    @GetMapping({"/read", "/edit"})
    // Spring의 파리미터를 자동으로 받아 tno저장
    public void read(Long tno,PageRequestDTO pageRequestDTO, Model model){
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
                           RedirectAttributes redirectAttributes,
                           PageRequestDTO pageRequestDTO){
        log.info("editPost");
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
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
    public String remove(Long tno,
                         PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes){
        // 리다이렉트 하는 페이지에서 page와size데이터를 사용할 수 있도록 설정
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        TodoDTO dto = todoService.getOne(tno);
        if(dto != null){
            todoService.removeTodo(tno);
            return "redirect:/todo/list";
        }else{
            return "redirect:/todo/list?errorMsg=error";
        }
    }
}
