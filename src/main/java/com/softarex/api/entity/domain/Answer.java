package com.softarex.api.entity.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NonNull
    private User asker;
    @OneToMany(cascade = CascadeType.PERSIST)
    //@JoinColumn(name = "answer_id")
    @NonNull
    private List<FieldAnswer> fieldAnswers = new ArrayList<>();
}
