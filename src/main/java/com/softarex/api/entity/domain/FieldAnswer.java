package com.softarex.api.entity.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class FieldAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NonNull
    private Answer answer;
    @ManyToOne
    @NonNull
    private Field field;
    @ElementCollection
    @CollectionTable(name = "field_answer_options", joinColumns = @JoinColumn(name = "field_answer_id"))
    @Column(name = "option")
    @NonNull
    private List<String> options;
}
