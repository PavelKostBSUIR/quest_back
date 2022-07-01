package com.softarex.api.mapper;

import com.softarex.api.entity.domain.Field;
import com.softarex.api.entity.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class FieldMapper {
    public abstract QuestionFieldDto fieldToQuestionFieldDto(Field field);

    public abstract Field createFieldDtoToField(CreateFieldDto createFieldDto);

    public abstract FieldIdDto fieldToFieldIdDto(Field field);

    public abstract FieldDto fieldToFieldDto(Field field);

    public abstract FieldInfoDto fieldToFieldInfoDto(Field field);
}
