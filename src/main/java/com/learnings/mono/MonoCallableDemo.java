package com.learnings.mono;


import com.learnings.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.util.List;

public class MonoCallableDemo {
    private static final Logger log = LoggerFactory.getLogger(MonoCallableDemo.class);

    public static void main(String[] args) {
        var list = List.of(1, 2, 3, 4, 5);
        // callable is similar to supplier, but it can throw checked exception - no need to manual handle checked exception
        Mono.fromCallable( () -> sum(list))
                .subscribe(Util.subscriber());
        // handling checked exception in fromSupplier
        Mono.fromSupplier(() -> {
                    try {
                        return sum(list);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .subscribe(Util.subscriber("fromSupplier"));
    }

    private static int sum(List<Integer> list) throws Exception{
        log.info("finding sum of {}", list);
        return list.stream().reduce(0, Integer::sum);
    }
}
