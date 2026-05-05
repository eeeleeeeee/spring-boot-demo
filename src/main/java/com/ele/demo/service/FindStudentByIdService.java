package com.ele.demo.service;

import com.ele.demo.repository.Student;
import com.ele.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindStudentByIdService {

    private final StudentRepository studentRepository;

    public Student execute(String id) {
        log.info("Fetching student by id: {}", id);
        return studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Student not found: {}", id);
                    return new RuntimeException("Student not found: " + id);
                });
    }
}
