package com.softarex.api.entity.dto;

import com.softarex.api.entity.domain.FieldType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class FieldDto {
    @NotNull
    @NonNull
    private Long id;
    @NotNull
    @NonNull
    private String label;
    @NotNull
    @NonNull
    private FieldType type;
    @NotNull
    @NonNull
    private List<String> options;
    @NotNull
    @NonNull
    private boolean required;
    @NotNull
    @NonNull
    private boolean active;
}
