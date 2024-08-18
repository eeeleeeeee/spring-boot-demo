package com.ele.demo;

import lombok.extern.flogger.Flogger;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("--- Non option args ---");
        args.getNonOptionArgs().forEach(log::info);
    }
}
