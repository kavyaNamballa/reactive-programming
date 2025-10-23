package com.learnings.operators;

import com.learnings.common.Util;
import reactor.core.publisher.Flux;

public class HandleDemo {
    public static void main(String[] args) {
        Flux<Integer> flux = Flux.range(1,10);
        flux.handle((item, sink) -> {
                    switch (item) {
                        case 1 -> sink.next(-2);
                        case 4 -> {}
                        case 7 -> sink.error(new Exception("error"));
                        default -> sink.next(item);
                    }
                })
                .subscribe(Util.subscriber());
    }
}
