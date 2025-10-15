package com.learnings.client;

import com.learnings.common.AbstractHttpClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ExternalServiceClient extends AbstractHttpClient {
    public Mono<String> getProductName(int productId) {
        return httpClient.get()
                .uri("/demo01/product/" + productId)
                .responseContent()
                .asString()
                .next(); // to convert Flux to Mono
    }

    public Flux<String> getProductNames() {
        return httpClient.get()
                .uri("/demo02/name/stream")
                .responseContent()
                .asString();
    }

    public Flux<Integer> getPriceChanges() {
        return httpClient.get()
                .uri("demo02/stock/stream")
                .responseContent()
                .asString()
                .map(Integer::parseInt);
    }
}
