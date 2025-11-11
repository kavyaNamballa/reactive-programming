package com.learnings.flux;

import com.learnings.client.ExternalServiceClient;
import com.learnings.common.Util;

public class NonBlockingDemo {
    public static void main(String[] args) throws InterruptedException {
        var client = new ExternalServiceClient();
        client.getProductNames()
                .subscribe(Util.subscriber());
        Thread.sleep(5000);
    }
}
