package com.ele.demo.listener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.GitProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ApplicationStartupListenerIT {

    @Autowired
    private GitProperties gitProperties;

    @Test
    void gitProperties_branchIsLoaded() {
        assertThat(gitProperties.getBranch()).isNotBlank();
    }

    @Test
    void gitProperties_commitIdIsLoaded() {
        assertThat(gitProperties.getCommitId()).isNotBlank();
    }
}
