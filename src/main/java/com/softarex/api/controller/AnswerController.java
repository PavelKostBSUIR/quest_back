package com.softarex.api.controller;

import com.softarex.api.entity.dto.AnswerDto;
import com.softarex.api.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/answers")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public void post(@RequestBody @Valid AnswerDto answerDto) {
        answerService.add(answerDto);
    }
}
