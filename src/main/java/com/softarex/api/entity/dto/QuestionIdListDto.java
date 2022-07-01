package com.softarex.api.entity.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class QuestionIdListDto {
    @NotNull
    @NonNull
    private List<QuestionIdDto> questionIds;
}