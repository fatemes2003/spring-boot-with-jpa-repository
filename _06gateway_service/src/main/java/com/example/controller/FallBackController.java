package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {
    @RequestMapping("ajab")
    public Mono<String> ajab()
    {
        return Mono.just("an error occurred!!!!!!!! ajab");
    }
}
