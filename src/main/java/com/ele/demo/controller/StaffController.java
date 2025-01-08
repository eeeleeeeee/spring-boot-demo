package com.ele.demo.controller;

import com.ele.demo.datasource.staff.pojo.Staff;
import com.ele.demo.datasource.staff.StaffRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/staff")
@RestController
public class StaffController {

    private final StaffRepository staffRepository;

    @PostMapping
    public ResponseEntity<String> save() {
        log.info("save");
        staffRepository.save(Staff.builder().name("ele").build());
        return new ResponseEntity<>("save success", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Staff>> get() {
        log.info("get");
        return new ResponseEntity<>(staffRepository.findAll(), HttpStatus.OK);
    }
}
