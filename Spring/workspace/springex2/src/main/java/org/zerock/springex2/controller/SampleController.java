package org.zerock.springex2.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex2.dto.TodoDTO;

import java.time.LocalDate;

@Controller
@Log4j2
public class SampleController {
    @GetMapping("/hello")
    // return이 없는 void로 메서드를 설정할 경우
    // 메서드의 이름과 같은 jsp를 찾아서 실행
    public void hello(){
        log.info("hello");
    }
    @GetMapping("/test")
    // 반환값을 String으로 설정한 후 return에 실행하고싶은 JSP파일을 설정
    public String asdasd(){
        log.info("test 실행");
        // req.getRequestDispatcher("/WEB-INF/views/test.jsp").forward(req,resp);
        // 서블릿에서 JSP페이지를 실행하던 코드를 아래와 같이 변경
        return "test";
    }
    @GetMapping("/redirectTest")
    // 반환값을 String으로 설정하고 return에 redirect:주소 리다이렉트를 실행
    public String redirectTest(){
        log.info("redirectTest 실행");
        //resp.sendRedirect("/hello")
        // 위의 내용을 스프링 컨트롤러 방식으로 설정
        return "redirect:/hello";
    }

    @GetMapping("/ex1")
    // 매개변수에 파라미터 이름을 설정하면 자동으로 파라미터를 읽고 데이터를 저장
    public void ex1(String name, int age){
//        String name = request.getParamater("name");
        log.info("ex1 name: "+name);
        log.info("ex1 age: "+age);
    }
    @GetMapping("/ex2")
    // 파라미터에 아무 데이터도 없을 경우 defaultValue를 기본값으로 설정
    public void ex2(@RequestParam(name="name", defaultValue="아무개") String name,
                    @RequestParam(name="age", defaultValue ="0") int age){
        log.info("ex2 name: "+name);
        log.info("ex2 age: "+age);
    }

    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate){
        log.info("ex3 date: "+dueDate);
    }
    @GetMapping("/ex4")
    public void ex4(Model model){
        // req.setAttiribute("key","value");
        // forward시 request에서 설정하던 데이터를 model에 설정하는것으로 변경
        model.addAttribute("message","컨트롤러에서 메시지를 설정했습니다.");
    }
    @PostMapping("/ex4_1")
    // 파라미터로 받아온 todoDTO의 경우 model에설정하지 않아도 자동으로 화면까지 전달함
    public void ex4_1(TodoDTO todoDTO, Model model){
        log.info(todoDTO);
        model.addAttribute("message", "메시지 설정");
    }
    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes){
        //RedirectAttributes : 리다이렉트 시 데이터를 저장할 수 있도록 하는 객체
        // addAttribute("key", "value") : 주소창에 설정되는 파라미터 설정
        redirectAttributes
                .addAttribute("name", "ABC");
        // addFlashAttribute(키,값) : 주소창에 설정되지 않는 JSP에서 1회용으로 사용하는 파라미터 설정
        redirectAttributes
                .addFlashAttribute("result","success");
        // 간단한 파라미터의 경우 return에 직접 작성하는 방법도 가능
        String title = "리다이렉트 파라미터 확인";
        return "redirect:/ex6?title="+title;
    }
    @GetMapping("/ex6")
    public void ex6(String name, String result){
        log.info(name);
        log.info(result);
    }
    @GetMapping("/ex7")
    public void ex7(String p1, int p2){
        log.info(p1);
        log.info(p2);
    }

}
