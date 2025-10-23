package com.learnings.flux;

import com.learnings.common.Util;
import reactor.core.publisher.Flux;

public class FluxCreate {
    public static void main(String[] args) {
        // flux is designed where we have only single subscriber
        conditionalComplete();
    }

    static void conditionalComplete() {
        Flux.create(fluxSink -> {
            String country;
            do {
                country = Util.faker().country().name();
                fluxSink.next(country);
            } while (!country.equals("Canada"));
            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }

    static void createFlux() {
        Flux.create(fluxSink -> {
            for (int i = 1; i <= 5; i++) {
                fluxSink.next(i);
            }
            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }
}
