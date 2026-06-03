package org.gargoil.springstalk.controller;

import lombok.RequiredArgsConstructor;
import org.gargoil.springstalk.service.interfaces.SpringStalkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SpringStalkController {
    final SpringStalkService springStalkService;

    @GetMapping("/")
    public String home() {
        return String.format("<h1>%s built with Spring Boot on Elastic Beanstalk</h1><p>Version: %s</p>", springStalkService.getName(), springStalkService.getVersion());
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
