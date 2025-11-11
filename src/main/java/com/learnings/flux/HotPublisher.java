package com.learnings.flux;

import com.learnings.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotPublisher {

    private static final Logger log = LoggerFactory.getLogger(HotPublisher.class);

    public static void main(String[] args) throws InterruptedException {
        // here share() is an alias for publish().refCount(1) - means it will start emitting
        // as soon as there is 1 subscriber
        // it stops when there is 0 subscribers
        // it starts afresh when a new subscriber comes in again - re-subscribe to the source
        var movieFlux = movieStream().share();
        Thread.sleep(2000);
        movieFlux.subscribe(Util.subscriber("kav"));
        Thread.sleep(3000);
        movieFlux
                .take(3)
                .subscribe(Util.subscriber("sai"));
        Thread.sleep(15000);
    }

    private static Flux<String> movieStream() {
        return Flux.generate(
                () -> {
                    log.info("Received the request");
                    return 1;
                },
                (state, sink) -> {
                    var scene = "Movie-Scene-" + state;
                    log.info("Playing: {}", scene);
                    sink.next(scene);
                    return ++state;
                }
                )
                .take(10)
                .delayElements(Duration.ofSeconds(1))
                .cast(String.class);
    }
}
