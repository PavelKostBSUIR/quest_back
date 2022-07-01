package com.softarex.api.repo;

import com.softarex.api.entity.domain.Answer;
import com.softarex.api.entity.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Page<Answer> findByAsker(User asker, Pageable pageable);
}
