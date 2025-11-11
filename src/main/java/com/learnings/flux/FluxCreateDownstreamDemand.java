package com.learnings.flux;

import com.learnings.common.Util;
import com.learnings.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

// Flux create does NOT check the downstream demand by default! It is by design!
public class FluxCreateDownstreamDemand {

    private static final Logger log = LoggerFactory.getLogger(FluxCreateDownstreamDemand.class);

    public static void main(String[] args) throws InterruptedException {

        produceOnDemand();

    }

    private static void produceEarly() throws InterruptedException {
        var subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {
            for (int i = 0; i < 10; i++) {
                var name = Util.faker().name().firstName();
                log.info("generated: {}", name);
                fluxSink.next(name);
            }
            fluxSink.complete();
        }).subscribe(subscriber);


        Thread.sleep(2000);
        subscriber.getSubscription().request(2);
        Thread.sleep(2000);
        subscriber.getSubscription().request(2);
        Thread.sleep(2000);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
    }

    private static void produceOnDemand() throws InterruptedException {
        var subscriber = new SubscriberImpl();
        Flux.<String>create(fluxSink -> {

            fluxSink.onRequest(request -> {
                for (int i = 0; i < request && !fluxSink.isCancelled(); i++) {
                    var name = Util.faker().name().firstName();
                    log.info("generated: {}", name);
                    fluxSink.next(name);
                }
            });


        }).subscribe(subscriber);


        Thread.sleep(2000);
        subscriber.getSubscription().request(2);
        Thread.sleep(2000);
        subscriber.getSubscription().request(2);
        Thread.sleep(2000);
        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
    }
}