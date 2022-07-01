package com.softarex.api.service;

import com.softarex.api.entity.domain.User;
import com.softarex.api.entity.dto.*;
import com.softarex.api.exception.ResourceNotFoundException;
import com.softarex.api.mapper.UserMapper;
import com.softarex.api.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto get(Long id, Principal principal) {
        User user = findUser(id);
        if (!user.getLogin().equals(principal.getName())) throw new BadCredentialsException("Permission denied");
        return userMapper.userToUserDto(user);
    }

    public UserIdDto add(CreateUserDto createUserDto) {
        return userMapper.userToUserIdDto(userRepository.save(userMapper.createUserDtoToUser(createUserDto)));
    }

    @Transactional
    public void update(Long id, UpdateUserDto updateUserDto, Principal principal) {
        User user = findUser(id);
        if (!user.getLogin().equals(principal.getName())) throw new BadCredentialsException("Permission denied");
        User newUser = userMapper.updateUserDtoToUser(updateUserDto);
        user.setLogin(newUser.getLogin());
        user.setName(newUser.getName());
        user.setSurname(newUser.getSurname());
        user.setPhoneNumber(newUser.getPhoneNumber());
        userRepository.save(user);
    }

    @Transactional
    public void update(Long id, UpdateUserPasswordDto updateUserPasswordDto, Principal principal) {
        User user = findUser(id);
        if (!user.getLogin().equals(principal.getName())) throw new BadCredentialsException("Permission denied");
        if (!updateUserPasswordDto.getOldPassword().equals(user.getPassword()))
            throw new BadCredentialsException("IncorrectPassword");
        user.setPassword(updateUserPasswordDto.getNewPassword());
        userRepository.save(user);
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
    }
}
