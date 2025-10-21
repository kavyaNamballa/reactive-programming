package com.learnings.flux;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Vector;

public class FluxSinkThreadSafety {

    private static final Logger log = LoggerFactory.getLogger(FluxSinkThreadSafety.class);

    public static void main(String[] args) {
        demo1();
    }

    // arraylist is not thread safe
    private static void demo1(){
//        var list = new Vector<Integer>();
        var list = new ArrayList<Integer>();
         // we expect 10000 items in the list but we get less than that because arraylist is not thread safe.
         // we can use vector which is thread safe but it is slower than arraylist.
         // we can also use Collections.synchronizedList(new ArrayList<>()) to make arraylist thread safe.
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };
        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("list size: {}", list.size());
    }

    // arraylist is not thread safe.
    // flux sink is thread safe. we get all the 10000 items safely into array list
    private static void demo2(){
        var list = new ArrayList<String>();
        var generator = new NameGenerator();
        var flux = Flux.create(generator);
        flux.subscribe(list::add);

        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                generator.generate();
            }
        };
        for (int i = 0; i < 10; i++) {
            Thread.ofPlatform().start(runnable);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("list size: {}", list.size());
    }

}
