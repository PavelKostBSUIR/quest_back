package com.softarex.api.entity.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class UpdateUserPasswordDto {

    @NotNull
    @NonNull
    @Size(min = 6)
    private String oldPassword;
    @NotNull
    @NonNull
    @Size(min = 6)
    private String newPassword;
}
