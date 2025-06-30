package me.shinsunyoung.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.shinsunyoung.springbootdeveloper.dto.AddUserRequest;
import me.shinsunyoung.springbootdeveloper.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    @PostMapping("/user")
    public String signup(AddUserRequest addUserRequest) {
        userService.save(addUserRequest);
        return "redirect:/login";
    }
}
