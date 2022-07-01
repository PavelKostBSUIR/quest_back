package com.softarex.api.entity.dto;

import lombok.*;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class UserAnswerPageDto {
    @NotNull
    @NonNull
    private Page<UserAnswerDto> userAnswerPage;
}
