package org.zerock.testproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// @Controller : 화면을 돌려주도록 설정하는 컨트롤러
// @RestController : 문자열, 숫자, 객체 등의 데이터를 돌려주도록 설정하는 컨트롤러
@RestController
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping("/test")
    public List<Member> getAllMembers(){
        List<Member> members = testService.getAllMembers();
        return members;
    }
}
