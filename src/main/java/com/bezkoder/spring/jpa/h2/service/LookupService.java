package com.bezkoder.spring.jpa.h2.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.bezkoder.spring.jpa.h2.model.Tutorial;
import com.bezkoder.spring.jpa.h2.repository.TutorialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

/**
 * LookupService
 */
@Slf4j
@Component
public class LookupService {

    @Autowired
	TutorialRepository gitHubLookupService;

    @Async
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public CompletableFuture<List<Tutorial>> lookUp() throws InterruptedException {
        CompletableFuture<List<Tutorial>> page1 = gitHubLookupService.findTop5ByPublished(false);
        String name = Thread.currentThread().getName();
        System.out.println("thread " + name + " is sleeping");
        Thread.sleep(6000L);
        System.out.println("thread " + name + " work up");
        return page1;
    }
     
}