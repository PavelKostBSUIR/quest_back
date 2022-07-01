package com.softarex.api.mapper;

import com.softarex.api.entity.domain.Answer;
import com.softarex.api.entity.domain.FieldAnswer;
import com.softarex.api.entity.dto.AnswerDto;
import com.softarex.api.entity.dto.FieldAnswerDto;
import com.softarex.api.entity.dto.UserAnswerDto;
import com.softarex.api.repo.FieldRepository;
import com.softarex.api.repo.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class AnswerMapper {

    @Autowired
    FieldRepository fieldRepository;
    @Autowired
    UserRepository userRepository;

    public abstract AnswerDto answerToAnswerDto(Answer answer);

    @Mapping(target = "asker", expression = "java(userRepository.findById(answerDto.getAskerId()).orElseThrow( com.softarex.api.exception.ResourceNotFoundException::new))")
    public abstract Answer answerDtoToAnswer(AnswerDto answerDto);

    @Mapping(target = "field", expression = "java(fieldRepository.findById(fieldAnswerDto.getFieldId()).orElseThrow( com.softarex.api.exception.ResourceNotFoundException::new))")
    public abstract FieldAnswer fieldAnswerDtoToFieldAnswer(FieldAnswerDto fieldAnswerDto);

    @Mapping(target = "fieldId", expression = "java(fieldAnswer.getField().getId())")
    public abstract FieldAnswerDto fieldAnswerToFieldAnswerDto(FieldAnswer fieldAnswer);

    public abstract UserAnswerDto answerToUserAnswerDto(Answer answer);
}
