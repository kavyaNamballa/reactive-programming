package com.learnings.common;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Mono;

public class Util {
    private static final Faker faker = Faker.instance();

    public static <T> Subscriber<T> subscriber() {
        return new GenericSubscriberImpl<>("");
    }

    public static Faker faker() {
        return faker;
    }

    public static <T> Subscriber<T> subscriber(String name) {
        return new GenericSubscriberImpl<>(name);
    }

    public static void main(String[] args) {
        var mono = Mono.just(1);
        mono.subscribe(subscriber());
        mono.subscribe(subscriber("First"));
    }
}
