package com.softarex.api.entity.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class QuestionIdDto {
    @NotNull
    @NonNull
    private Long id;
}
