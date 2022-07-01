package com.softarex.api.mapper;

import com.softarex.api.entity.domain.Role;
import com.softarex.api.entity.domain.User;
import com.softarex.api.entity.dto.*;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class UserMapper {
    @BeforeMapping
    protected void enrichDTOWithRoles(@MappingTarget User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        user.setRoles(roles);

    }

    public abstract QuestionIdDto userToQuestionIdDto(User user);

    public abstract UserDto userToUserDto(User user);

    public abstract User createUserDtoToUser(CreateUserDto createUserDto);

    public abstract UserIdDto userToUserIdDto(User user);

    public abstract User updateUserDtoToUser(UpdateUserDto updateUserDto);
}
