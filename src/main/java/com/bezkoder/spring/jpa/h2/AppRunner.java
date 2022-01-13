package com.bezkoder.spring.jpa.h2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

import com.bezkoder.spring.jpa.h2.model.Tutorial;
import com.bezkoder.spring.jpa.h2.repository.TutorialRepository;
import com.bezkoder.spring.jpa.h2.service.LookupService;

@Component
public class AppRunner implements CommandLineRunner {

  private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);
  private AtomicInteger counter = new AtomicInteger();

//   private final TutorialRepository gitHubLookupService;
  @Autowired
	TutorialRepository gitHubLookupService;

    @Autowired LookupService lookupService;
//   public AppRunner(GitHubLookupService gitHubLookupService) {
//     this.gitHubLookupService = gitHubLookupService;
//   }

  @Override
  @Transactional
  public void run(String... args) throws Exception {
    // Start the clock
    while (true) {
        
    
    long start = System.currentTimeMillis();

    // Kick of multiple, asynchronous lookups
    // CompletableFuture<List<Tutorial>> page1 = gitHubLookupService.findTop5ByPublished(false);
    // CompletableFuture<List<Tutorial>> page2 = gitHubLookupService.findTop5ByPublished(false);
    // CompletableFuture<List<Tutorial>> page3 = gitHubLookupService.findTop5ByPublished(false);

    CompletableFuture<List<Tutorial>> page1 = lookupService.lookUp();
    CompletableFuture<List<Tutorial>> page2 = lookupService.lookUp();
    CompletableFuture<List<Tutorial>> page3 = lookupService.lookUp();


    // Wait until they are all done
    // CompletableFuture.allOf(page1,page2,page3).join();

    // Print results, including elapsed time
    logger.info("Elapsed time: " + (System.currentTimeMillis() - start) + " counter " + counter.incrementAndGet());
    logger.info("page1--> " + page1.get());
    logger.info("page2--> " + page2.get());
    logger.info("page3--> " + page3.get());
    Thread.sleep(6009);
    }

  }

}