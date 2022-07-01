package com.softarex.api.service;

import com.softarex.api.entity.dto.AnswerDto;
import com.softarex.api.mapper.AnswerMapper;
import com.softarex.api.repo.AnswerRepository;
import com.softarex.api.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AnswerService {

    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public AnswerService(UserRepository userRepository, AnswerRepository answerRepository, AnswerMapper answerMapper, SimpMessagingTemplate messagingTemplate) {
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
        this.messagingTemplate = messagingTemplate;
    }

    @Transactional
    public void add(AnswerDto answerDto) {
        answerRepository.save(answerMapper.answerDtoToAnswer(answerDto));
        messagingTemplate.convertAndSend("", "update");
        //todo can be error cause of cascade
        //Answer finalAnswer = answer;
        // List<FieldAnswer> fieldAnswers = answerDto.getFieldAnswers().stream().map(fieldAnswerDto -> fieldAnswerService.fromDto(fieldAnswerDto, finalAnswer)).collect(Collectors.toList());
        //fieldAnswerRepository.saveAll(fieldAnswers);
    }
}
