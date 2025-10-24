package com.learnings.flux;

import com.learnings.common.Util;
import reactor.core.publisher.Flux;

public class FluxCreateRefactored {
    public static void main(String[] args) {
        var nameGenerator = new NameGenerator();
        var flux = Flux.create(nameGenerator);
        flux.subscribe(Util.subscriber("sub1"));
        flux.subscribe(Util.subscriber("sub2"));
        for (int i = 0; i < 5; i++) {
            nameGenerator.generate();
        }
    }
}
