package com.softarex.api.entity.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class FieldInfoDto {
    @NotNull
    @NonNull
    private Long id;
    @NotNull
    @NonNull
    private String label;
}
