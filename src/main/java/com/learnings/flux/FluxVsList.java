package com.learnings.flux;

import com.learnings.common.Util;
import com.learnings.subscriber.SubscriberImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.IntStream;

public class FluxVsList {

    private static final Logger log = LoggerFactory.getLogger(FluxVsList.class);

    public static void main(String[] args) {
//        var list = getNamesList(5);
//        System.out.println(list);
//        getNames(10)
//                .subscribe(Util.subscriber());
        var subscriber = new SubscriberImpl();
        getNames(10)
                .subscribe(subscriber);
        subscriber.getSubscription().request(3);

    }

    static List<String> getNamesList(int count) {
        return IntStream.rangeClosed(1, count)
                .mapToObj(i-> generateName())
                .toList();
    }

    static Flux<String> getNames(int count) {
        return Flux.range(1, count)
                .map(i -> generateName());
    }

    static String generateName() {
        log.info("Generating name...");
        try {
            Thread.sleep(1000);
        } catch (Exception _) {

        }
        return Util.faker().name().firstName();
    }
}
