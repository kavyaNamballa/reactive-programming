package com.learnings.mono;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoDeferDemo {
    private static final Logger log = LoggerFactory.getLogger(MonoDeferDemo.class);

    public static void main(String[] args) {
        // to avoid eager calculation, we can use fromSupplier - but it excutes creation of publisher eagerly
//        createPublisher();
//                .subscribe(Util.subscriber());
        // so to avoid that we can use defer which will create the publisher only when there is a subscription
        // used when the publisher creation is expensive
        Mono.defer(() -> createPublisher());
//                .subscribe(Util.subscriber());
    }

    private static Mono<Integer> createPublisher() {
        log.info("creating publisher");
        var list = List.of(1, 2, 3, 4, 5);
        try {
            Thread.sleep(3000); // simulating expensive operation
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Mono.fromSupplier(() -> sum(list));
    }
    private static int sum(List<Integer> list) {
        log.info("finding sum of {}", list);
        return list.stream().reduce(0, Integer::sum);
    }
}
