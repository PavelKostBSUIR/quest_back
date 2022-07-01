package com.softarex.api.entity.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String label;
    @NonNull
    private boolean required;
    @NonNull
    private boolean active;
    @Enumerated(EnumType.STRING)
    @NonNull
    private FieldType type;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "field_options", joinColumns = @JoinColumn(name = "field_id"))
    @Column(name = "option")
    @NonNull
    private List<String> options;
    @ManyToOne
    @NonNull
    private User asker;
    @OneToMany(mappedBy = "field", cascade = CascadeType.REMOVE)
    @NonNull
    private Set<FieldAnswer> fieldAnswers;

}
