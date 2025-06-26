package org.zerock.tourist_springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(){

        return "index";
    }

}

//순서 정리 예시
//DB 테이블 생성 + 테스트 데이터 삽입
//
//Program 엔티티 (domain)
//
//ProgramRepository (repository)
//
//ProgramService (선택)
//
//ProgramController (controller)
//
//program.html (templates/program/)
//
//header.html, footer.html (templates/fragments/)
//
//static/ 하위 폴더에 이미지/CSS/JS 구성