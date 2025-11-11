package com.learnings.mono;


import com.learnings.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoSupplierDemo {
    private static final Logger log = LoggerFactory.getLogger(MonoSupplierDemo.class);

    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4, 5);
        // here though sum is calculated eagerly, but Mono will emit the value only when someone subscribes to it
//        Mono.just(sum(list))
//                .subscribe(Util.subscriber());
        // to avoid eager calculation, we can use fromSupplier
        Mono.fromSupplier(() -> sum(list))
                .subscribe(Util.subscriber());
    }

    private static int sum(List<Integer> list) {
        log.info("finding sum of {}", list);
        return list.stream().reduce(0, Integer::sum);
    }
}
