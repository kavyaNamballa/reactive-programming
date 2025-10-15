package com.learnings.mono;

import com.learnings.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

public class MonoRunnableDemo {
    private static final Logger log = LoggerFactory.getLogger(MonoRunnableDemo.class);

    public static void main(String[] args) {
        getProductName(2)
                .subscribe(Util.subscriber());
        Mono<Integer> mono = Mono.empty();
    }

    private static Mono<String> getProductName(int productId) {
        if(productId == 1) return Mono.fromSupplier(() -> Util.faker().commerce().productName());
//        return Mono.empty();
        // to run a side effect when the Mono is empty - doesn't disturb the type of overall operation chain
        return Mono.fromRunnable(() -> notifyBusiness(productId));
    }

    private static void notifyBusiness(int productId) {
        log.info("Notifying business for product id {}", productId);
    }
}
