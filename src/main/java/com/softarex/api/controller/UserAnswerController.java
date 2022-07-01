package com.softarex.api.controller;

import com.softarex.api.entity.dto.UserAnswerPageDto;
import com.softarex.api.service.UserAnswerService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users/{id}/answers")
public class UserAnswerController {
    private final UserAnswerService userAnswerService;

    public UserAnswerController(UserAnswerService userAnswerService) {
        this.userAnswerService = userAnswerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public UserAnswerPageDto getPage(@PathVariable("id") Long id, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Principal principal) {
        return userAnswerService.getPage(pageable, principal, id);
    }


}
