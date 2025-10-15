package com.learnings.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileServiceImpl implements FileService{

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    private static final Path PATH = Path.of("src/main/resources/");

    @Override
    public Mono<String> readFile(String filePath) {
        return Mono.fromCallable(() -> Files.readString(PATH.resolve(filePath)))
                .doOnError(e -> logger.error("Error reading file: {}", filePath, e));
    }

    @Override
    public Mono<Void> writeFile(String filePath, String fileContent) {
        return Mono.fromRunnable(() -> this.write(filePath, fileContent));
    }

    @Override
    public Mono<Void> deleteFile(String filePath) {
        return Mono.fromRunnable(() -> this.delete(filePath));
    }

    private void delete(String filePath) {
        try {
            Files.delete(PATH.resolve(filePath));
            logger.info("Deleted file: {}", filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void write(String fileName, String content) {
        try {
            Files.writeString(PATH.resolve(fileName), content);
            logger.info("File written: {}", fileName);
        } catch (Exception e) {
            logger.error("Error writing file: {}", fileName, e);
        }
    }
}
