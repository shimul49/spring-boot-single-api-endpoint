package io.naztech.reflect.controller;


import io.naztech.reflect.dto.DesignationDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DesignationController {
    @GetMapping("/designation")
    public String designation(@RequestBody DesignationDto designationDto) {
        return "Hello " + designationDto.getName();
    }
}
