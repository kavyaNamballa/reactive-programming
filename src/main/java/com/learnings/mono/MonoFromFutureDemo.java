package com.learnings.mono;

import com.learnings.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class MonoFromFutureDemo {
    private static final Logger log = LoggerFactory.getLogger(MonoFromFutureDemo.class);

    public static void main(String[] args) throws InterruptedException {
        // here getName is executed though we didn't subscribe to the Mono
        Mono.fromFuture(getName());
//        Mono.fromFuture(() -> getName());
//                .subscribe(Util.subscriber());
        Thread.sleep(1000); // to keep the main thread alive to see the async result
    }

    private static CompletableFuture<String> getName() {
        return CompletableFuture.supplyAsync(() -> {
            log.info("generating name");
            return Util.faker().name().fullName();
        });
    }
}
