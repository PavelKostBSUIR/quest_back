package com.softarex.api.service;

import com.softarex.api.entity.domain.Field;
import com.softarex.api.entity.domain.User;
import com.softarex.api.entity.dto.*;
import com.softarex.api.exception.ResourceNotFoundException;
import com.softarex.api.mapper.FieldMapper;
import com.softarex.api.repo.FieldRepository;
import com.softarex.api.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.stream.Collectors;

@Service
public class UserFieldService {

    private final FieldRepository fieldRepository;
    private final UserRepository userRepository;
    private final FieldMapper fieldMapper;

    @Autowired
    public UserFieldService(FieldRepository fieldRepository, UserRepository userRepository, FieldMapper fieldMapper) {
        this.fieldRepository = fieldRepository;
        this.userRepository = userRepository;
        this.fieldMapper = fieldMapper;
    }

    public FieldPageDto getPage(Pageable pageable, Long userId, Principal principal) {
        User user = findUser(userId);
        if (!user.getLogin().equals(principal.getName())) throw new BadCredentialsException("Permission denied");
        return new FieldPageDto(fieldRepository.findByAskerId(userId, pageable).map(fieldMapper::fieldToFieldDto));
    }

    public FieldListDto getAll(Long userId, Principal principal) {
        User user = findUser(userId);
        if (!user.getLogin().equals(principal.getName())) throw new BadCredentialsException("Permission denied");
        return new FieldListDto(fieldRepository.findByAskerIdAndActiveTrue(userId).orElseThrow(ResourceNotFoundException::new).stream().map(fieldMapper::fieldToFieldInfoDto).collect(Collectors.toList()));
    }

    @Transactional
    public FieldIdDto add(CreateFieldDto createFieldDto, Principal principal, Long userId) {
        User user = findUser(userId);
        if (!user.getLogin().equals(principal.getName())) throw new BadCredentialsException("Permission denied");
        Field field = fieldMapper.createFieldDtoToField(createFieldDto);
        field.setAsker(user);
        //userRepository.save(asker);//todo delete after registration
        field = fieldRepository.save(field);
        return fieldMapper.fieldToFieldIdDto(field);
    }

    @Transactional
    public void update(CreateFieldDto createFieldDto, Principal principal, Long userId, Long fieldId) {
        User user = findUser(userId);
        if (!user.getLogin().equals(principal.getName())) throw new BadCredentialsException("Permission denied");
        if (!fieldRepository.existsById(fieldId)) throw new ResourceNotFoundException();
        Field field = fieldMapper.createFieldDtoToField(createFieldDto);
        field.setAsker(user);
        field.setId(fieldId);
        fieldRepository.save(field);//todo delete after registration
    }

    public FieldDto get(Long fieldId, Long userId, Principal principal) {
        Field field = findAndCheckField(fieldId, userId, principal);
        return fieldMapper.fieldToFieldDto(field);
    }

    public void delete(Long fieldId, Long userId, Principal principal) {
        findAndCheckField(fieldId, userId, principal);
        fieldRepository.deleteById(fieldId);
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
    }

    private Field findAndCheckField(Long fieldId, Long userId, Principal principal) {
        User user = findUser(userId);
        if (!user.getLogin().equals(principal.getName())) throw new BadCredentialsException("Permission denied");
        Field field = fieldRepository.findById(fieldId).orElseThrow(ResourceNotFoundException::new);
        if (!field.getAsker().getId().equals(userId)) throw new BadCredentialsException("Permission denied");
        return field;
    }

}
