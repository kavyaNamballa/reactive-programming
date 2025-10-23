package com.learnings.flux;

import com.learnings.common.Util;
import reactor.core.publisher.Flux;

public class FluxGenerateDemo {
    public static void main(String[] args) {
        // we can emit only one item at a time in generate method
        Flux.generate(synchronousSink -> {
                    synchronousSink.next("Hello Flux Generate");
                    synchronousSink.complete();
                }
        ).subscribe(Util.subscriber());
        fluxGenerateWithState();
//        twoEmissions();
//        controlDownstreamDemand();
    }

    static void fluxGenerateWithState() {
        Flux.generate(
                () -> 0,
                (cnt, sink) -> {
                    var country = Util.faker().country().name();
                    sink.next(country);
                    cnt++;
                    if (cnt >= 10 || country.equals("Canada")) {
                        sink.complete();
                    }
                    return cnt;
                }
        ).subscribe(Util.subscriber());
    }

    static void controlDownstreamDemand() {
        // runs the lambda again and again until the take condition is met
        // until the downstream demand or completion or cancellation or error
        Flux.generate(synchronousSink -> {
                    synchronousSink.next(1);
                }
        ).take(3)
         .subscribe(Util.subscriber());
    }

    static void twoEmissions() {
        Flux.generate(synchronousSink ->
            {
                synchronousSink.next(1);
                synchronousSink.next(2);
                synchronousSink.complete();
            }
        ).subscribe(Util.subscriber());
    }
}
