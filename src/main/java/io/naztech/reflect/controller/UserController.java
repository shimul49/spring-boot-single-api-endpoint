package io.naztech.reflect.controller;


import io.naztech.reflect.dto.UserDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    @GetMapping("/hello")
    public String helloUser( @RequestBody Map<String, Object> args ) {
        return "Hello " + args.get("fullName");
    }
}
