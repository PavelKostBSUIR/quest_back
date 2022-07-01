package com.softarex.api.repo;

import com.softarex.api.entity.domain.Field;
import com.softarex.api.entity.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FieldRepository extends JpaRepository<Field, Long> {
    Page<Field> findByAsker(User asker, Pageable pageable);

    Page<Field> findByAskerId(Long askerId, Pageable pageable);

    List<Field> findByAskerId(Long askerId);

    Optional<List<Field>> findByAskerIdAndActiveTrue(Long askerId);
}
