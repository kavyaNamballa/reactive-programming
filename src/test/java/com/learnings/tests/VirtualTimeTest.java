package com.learnings.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class VirtualTimeTest {

    private Flux<Integer> getItems() {
        return Flux.range(1,5)
                .log()
                .delayElements(Duration.ofSeconds(5));
    }

    @Test
    public void demo() {
        StepVerifier.create(getItems())
                .expectNext(1,2,3,4,5)
                .expectComplete()
                .verify();
    }

    @Test
    public void demo2() {
        StepVerifier.withVirtualTime(this::getItems)
                .thenAwait(Duration.ofSeconds(26))
                .expectNext(1,2,3,4,5)
                .expectComplete()
                .verify();
    }

}
