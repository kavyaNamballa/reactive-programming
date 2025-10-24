package com.learnings.flux;

import com.learnings.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

/*
    - publish().autoConnect(0) will provide new values to the subscribers.
    - replay allows us to cache
 */
public class HotPublisherCache {

    private static final Logger log = LoggerFactory.getLogger(HotPublisherCache.class);

    public static void main(String[] args) throws InterruptedException {

        var stockFlux = stockStream().replay(1).autoConnect(0);

        Thread.sleep(4000);

        log.info("sam joining");
        stockFlux
                .subscribe(Util.subscriber("sam"));

        Thread.sleep(4000);

        log.info("mike joining");
        stockFlux
                .subscribe(Util.subscriber("mike"));

        Thread.sleep(15000);

    }

    private static Flux<Integer> stockStream() {
        return Flux.generate(sink -> sink.next(Util.faker().random().nextInt(10, 100)))
                .delayElements(Duration.ofSeconds(3))
                .doOnNext(price -> log.info("emitting price: {}", price))
                .cast(Integer.class);
    }

}