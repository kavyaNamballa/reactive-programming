package com.learnings;

import com.learnings.client.ExternalServiceClient;
import com.learnings.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NonBlockingIO {
    private static final Logger log = LoggerFactory.getLogger(NonBlockingIO.class);

    public static void main(String[] args) throws InterruptedException {
        var client = new ExternalServiceClient();

        for (int i = 1; i <= 100; i++) {
            // here getProductName is non-blocking, so all the calls are made in a short span of time
            // and the responses are handled whenever they arrive
            // if it was blocking, then each call would wait for the response before making the next call
//            String name = client.getProductName(i)
//                            .block();
//            log.info(name);
            // to see the non-blocking behaviour, comment the above line and uncomment below line
            client.getProductName(i)
//                    .doOnNext(data -> log.info("Received on thread: {}", Thread.currentThread().getName()))
                    .subscribe(Util.subscriber("call-" + i));
        }
        Thread.sleep(2000);
    }
}
