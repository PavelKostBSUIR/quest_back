package com.softarex.api.service;

import com.softarex.api.entity.dto.QuestionDto;
import com.softarex.api.entity.dto.QuestionIdListDto;
import com.softarex.api.exception.ResourceNotFoundException;
import com.softarex.api.mapper.FieldMapper;
import com.softarex.api.mapper.UserMapper;
import com.softarex.api.repo.FieldRepository;
import com.softarex.api.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class QuestionService {
    private final FieldRepository fieldRepository;
    private final UserRepository userRepository;
    private final FieldMapper fieldMapper;
    private final UserMapper userMapper;

    @Autowired
    public QuestionService(FieldRepository fieldRepository, UserRepository userRepository, FieldMapper fieldMapper, UserMapper userMapper) {
        this.fieldRepository = fieldRepository;
        this.userRepository = userRepository;
        this.fieldMapper = fieldMapper;
        this.userMapper = userMapper;
    }

    public QuestionDto findById(Long id) {
        return new QuestionDto(fieldRepository.findByAskerIdAndActiveTrue(id).orElseThrow(ResourceNotFoundException::new).stream().map(fieldMapper::fieldToQuestionFieldDto).collect(Collectors.toList()));
    }

    public QuestionIdListDto findAll() {
        return new QuestionIdListDto(userRepository.findAll().stream().map(userMapper::userToQuestionIdDto).collect(Collectors.toList()));
    }


}
