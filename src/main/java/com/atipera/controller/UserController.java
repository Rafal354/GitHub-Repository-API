package com.atipera.controller;

import com.atipera.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{username}/repos")
    public ResponseEntity<?> getUserRepositoriesInfo(@RequestHeader("Accept") String header,
                                                     @PathVariable("username") String username) {
        return userService.getUserRepositoriesInfo(username, header);
    }
}
