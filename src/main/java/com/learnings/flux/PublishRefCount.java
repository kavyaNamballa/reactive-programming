package com.learnings.flux;

import reactor.core.publisher.Flux;

public class PublishRefCount {
    public static void main(String[] args) {
        Flux<Object> flux = Flux.create(fluxSink -> {
                    System.out.println("created");
                    for (int i = 0; i < 5; i++) {
                        fluxSink.next(i);
                    }
                    fluxSink.complete();
                })
                .log()
                .publish()
                .refCount(2);
        flux.subscribe(System.out::println);
        flux.subscribe(System.out::println);
        flux.subscribe(System.out::println);
    }
}
