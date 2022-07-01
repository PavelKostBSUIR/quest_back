package com.softarex.api.service;

import com.softarex.api.entity.domain.User;
import com.softarex.api.entity.dto.UserAnswerPageDto;
import com.softarex.api.exception.ResourceNotFoundException;
import com.softarex.api.mapper.AnswerMapper;
import com.softarex.api.repo.AnswerRepository;
import com.softarex.api.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserAnswerService {
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;


    @Autowired
    public UserAnswerService(UserRepository userRepository, AnswerRepository answerRepository, AnswerMapper answerMapper) {
        this.userRepository = userRepository;
        this.answerRepository = answerRepository;
        this.answerMapper = answerMapper;
    }

    public UserAnswerPageDto getPage(Pageable pageable, Principal principal, Long id) {
        User user = userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        if (!user.getLogin().equals(principal.getName()))
            throw new BadCredentialsException("Permission denied");
        return new UserAnswerPageDto(answerRepository.findByAsker(user, pageable).map((answerMapper::answerToUserAnswerDto)));
    }


}
