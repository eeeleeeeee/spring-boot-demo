package com.ele.demo.controller;

import com.ele.demo.service.async.NotificationService;
import com.ele.demo.service.waitCompletion.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Async Controller")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/async")
public class AsyncController {

    private final NotificationService notificationService;
    private final FileService fileService;

    @GetMapping
    public ResponseEntity<?> async() {
        try {
            notificationService.send();
            return ResponseEntity.ok("The async task has started");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("wait-for-completion")
    public ResponseEntity<?> waitForCompletion() {
        try {
            String urlToDownload = "https://fastly.picsum.photos/id/10/2500/1667.jpg?hmac=J04WWC_ebchx3WwzbM-Z4_KC_LeLBWr5LZMaAkWkF68";

            String fileName = fileService.start(urlToDownload);

            return ResponseEntity.ok("The async task has completed. And the downloaded fileName is : " + fileName);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
