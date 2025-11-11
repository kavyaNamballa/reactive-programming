package com.learnings.files;

import reactor.core.publisher.Mono;

import java.io.IOException;

public interface FileService {
    Mono<String> readFile(String filePath) throws IOException;

    Mono<Void> writeFile(String filePath, String fileContent);

    Mono<Void> deleteFile(String filePath);
}
