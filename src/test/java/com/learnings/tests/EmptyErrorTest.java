package com.learnings.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class EmptyErrorTest {
    Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new Exception("invalid user id"));
        };
    }

    @Test
    public void userTest() {
        StepVerifier.create(getUsername(1))
                .expectNext("sam")
                .expectComplete()
                .verify();
    }

    @Test
    public void emptyTest() {
        StepVerifier.create(getUsername(2))
                .expectComplete()
                .verify();
    }

    @Test
    public void errorTest() {
        StepVerifier.create(getUsername(3))
                .expectError()
                .verify();
    }

    @Test
    public void errorMessageTest() {
        StepVerifier.create(getUsername(3))
                .expectErrorMessage("invalid user id")
                .verify();
    }

}
