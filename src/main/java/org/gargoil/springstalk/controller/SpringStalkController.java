package org.gargoil.springstalk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gargoil.springstalk.repository.VisitRepository;
import org.gargoil.springstalk.service.interfaces.SpringStalkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SpringStalkController {
    final SpringStalkService springStalkService;
    final VisitRepository visitRepository;

    @GetMapping("/")
    public String home() {
        log.info("GET / - handling home request");
        try {
            long visits = visitRepository.increment();
            log.info("GET / - served (name={}, version={}, visits={})", springStalkService.getName(), springStalkService.getVersion(), visits);
            return String.format(
                    "<h1>%s built with Spring Boot on Elastic Beanstalk</h1><p>Version: %s</p><p>Visits: %d</p>",
                    springStalkService.getName(),
                    springStalkService.getVersion(),
                    visits
            );
        } catch (RuntimeException e) {
            log.error("GET / - failed to increment visit counter", e);
            throw e;
        }
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
