package com.softarex.api.entity.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class FieldAnswerDto {

    @NotNull
    @NonNull
    private Long fieldId;
    @NonNull
    private List<String> options;


}
