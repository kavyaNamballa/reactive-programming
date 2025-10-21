package com.learnings.flux;

import com.learnings.common.Util;
import reactor.core.publisher.Flux;

public class FluxGenerateDemo {
    public static void main(String[] args) {
        Flux.generate(synchronousSink ->
                synchronousSink.next("Hello Flux Generate")
        ).subscribe(Util.subscriber());
    }
}
