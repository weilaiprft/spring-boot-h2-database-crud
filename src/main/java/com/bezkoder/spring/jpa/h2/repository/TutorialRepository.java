package com.bezkoder.spring.jpa.h2.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;

import com.bezkoder.spring.jpa.h2.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({@QueryHint(name = "javax.persistence.lock.timeout", value ="-2")})
    CompletableFuture<List<Tutorial>> findTop5ByPublished(boolean published);

    // List<Tutorial> findByPublished(boolean published);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Tutorial> findByTitleContaining(String title);
}
