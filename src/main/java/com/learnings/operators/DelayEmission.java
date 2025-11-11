package com.learnings.operators;

import com.learnings.common.Util;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class DelayEmission {
    public static void main(String[] args) throws InterruptedException {
        Flux.range(1,10)
                .log()
                .delayElements(Duration.ofSeconds(1))
                .subscribe(Util.subscriber());
        Thread.sleep(12000);
    }
}
