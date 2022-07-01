package com.softarex.api.controller;

import com.softarex.api.entity.dto.*;
import com.softarex.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{id}")
    public UserDto get(@PathVariable Long id, Principal principal) {
        return userService.get(id, principal);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserIdDto post(@RequestBody @Valid CreateUserDto createUserDto) {
        return userService.add(createUserDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}")
    public void put(@PathVariable("id") Long id, @RequestBody @Valid UpdateUserDto updateUserDto, Principal principal) {
        userService.update(id, updateUserDto, principal);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/{id}/password")
    public void put(@PathVariable("id") Long id, @RequestBody @Valid UpdateUserPasswordDto updateUserPasswordDto, Principal principal) {
        userService.update(id, updateUserPasswordDto, principal);
    }
}
