package com.ele.demo.service.waitCompletion;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileService {

    private final DownloadFileAsyncService downloadFileAsyncService;

    public String start(String urlToDownload) throws Exception {
        CompletableFuture<String> future = downloadFileAsyncService.start(urlToDownload);
        return future.get();
    }
}
