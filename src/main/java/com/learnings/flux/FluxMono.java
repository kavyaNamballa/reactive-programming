package com.learnings.flux;

import com.learnings.common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxMono {
    public static void main(String[] args) {
        var mono = getUsername(1);
        save(Flux.from(mono));
    }

    private static Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("admin");
            case 2 -> Mono.empty();
            default -> Mono.error(new Exception("invalid user id"));
        };
    }

    private static void save(Flux<String> flux) {
        flux.subscribe(Util.subscriber());
    }
}
