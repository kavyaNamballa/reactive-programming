package com.learnings.flux;

import com.learnings.common.Util;
import reactor.core.publisher.Flux;

import java.util.List;

public class FluxDemo {
    public static void main(String[] args) {
//        justDemo();
//        fromStream();
//        fromRange();
        takeOperators();
    }

    private static void justDemo() {
        Flux.just(1,2,3)
                .subscribe(Util.subscriber());
        Flux.just(1,2,3,4,5,6)
                .filter(i -> i % 2 == 0)
                .map(i -> i * 2)
                .subscribe(Util.subscriber());
    }

    static void fromCollections() {
        var list = List.of(1,2,3,4);
        Integer[] array = {1,2,3,4};
        Flux.fromIterable(list)
                .subscribe(Util.subscriber());
        Flux.fromArray(array)
                .subscribe(Util.subscriber());
    }

    static void fromStream() {
        var list = List.of(1,2,3,4);
        var stream = list.stream();
        Flux.fromStream(stream)
                .subscribe(Util.subscriber());
        Flux.fromStream(stream)
                .subscribe(Util.subscriber());
        // to fix the above issue, we can use a supplier to create a new stream each time
        Flux.fromStream(() -> list.stream())
                .subscribe(Util.subscriber());

    }

    static void fromRange() {
        Flux.range(3,5)
                .log()
                .subscribe(Util.subscriber());
    }

    static void takeOperators() {
        Flux.range(1,10)
                .log("take")
                .take(3)
                .log("sub")
                .subscribe(Util.subscriber());
    }
}
