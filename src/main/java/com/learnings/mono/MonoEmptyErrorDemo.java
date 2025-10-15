package com.learnings.mono;

import com.learnings.common.Util;
import reactor.core.publisher.Mono;

public class MonoEmptyErrorDemo {
    public static void main(String[] args) {
        getUsername(1).subscribe(Util.subscriber());
        getUsername(2).subscribe(Util.subscriber());
        getUsername(3).subscribe(Util.subscriber());
        // let's say I've used consumer based subscribe - here as I didn't provide error consumer, it will throw an exception
        getUsername(4).subscribe(System.out::println);
    }

    private static Mono<String> getUsername(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("Kavya");
            case 2 -> Mono.empty(); // represents no value - completes without emitting any item
            default -> Mono.error(new RuntimeException("User not found"));
        };
    }
}
