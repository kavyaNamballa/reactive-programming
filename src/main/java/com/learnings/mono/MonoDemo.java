package com.learnings.mono;

import com.learnings.subscriber.SubscriberImpl;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

public class MonoDemo {
    public static void main(String[] args) {
//        usingConsumer();
//        usingSubscriber();
        usingConsumerError();
    }

    static void usingConsumer() {
        Mono<String> mono = Mono.just("Hello");
        // using overloaded method which takes a consumer
        mono.subscribe(System.out::println, System.err::println, () -> System.out.println("Completed"));
//        mono.subscribe(System.out::println)
    }

    static void usingConsumerError() {
        var mono = Mono.just(1).map(i -> i/0);
        mono.subscribe(System.out::println, System.err::println, () -> System.out.println("Completed"));
    }

    static void usingSubscriber() {
        // mono is a publisher that emits 0 or 1 item
        Mono<String> mono = Mono.just("Hello");
        // it doesn't emit anything until we subscribe to it
        System.out.println(mono);

        // subscribe to the mono;
        var subscriber = new SubscriberImpl();
        mono.subscribe(subscriber); // not prints anything until request is made
//        subscriber.getSubscription().cancel();
        subscriber.getSubscription().request(2);
        subscriber.getSubscription().request(2);
        Consumer<String> consumer = System.out::println;
        // each time we subscribe, a new subscription is created that's why it prints "Hello" each time
        mono.subscribe(System.out::println);
        mono.subscribe(System.out::println);
    }
}
