package com.learnings.flux;

import com.learnings.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class HotPublisherAutoConnect {

    private static final Logger log = LoggerFactory.getLogger(HotPublisherAutoConnect.class);

    public static void main(String[] args) throws InterruptedException {
        // publish().autoConnect() - does not stop when subscribers cancel.
        // it will start producing even for 0 subscribers once the specified number of subscribers have connected.
        var movieFlux = movieStream().publish().autoConnect();
        Thread.sleep(2000);
        movieFlux
                .take(2)
                .subscribe(Util.subscriber("kav"));
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
