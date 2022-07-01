package com.softarex.api.controller;

import com.softarex.api.entity.dto.QuestionDto;
import com.softarex.api.entity.dto.QuestionIdListDto;
import com.softarex.api.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("{id}")
    public QuestionDto get(@PathVariable Long id) {
        return questionService.findById(id);
    }

    @GetMapping()
    public QuestionIdListDto getAll() {
        return questionService.findAll();
    }


}
