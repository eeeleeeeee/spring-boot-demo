package com.ele.demo.service;

import com.ele.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteStudentService {

    private final StudentRepository studentRepository;
    private final FindStudentByIdService findStudentByIdService;

    public void execute(String id) {
        log.info("Deleting student: {}", id);
        findStudentByIdService.execute(id);
        studentRepository.deleteById(id);
        log.info("Deleted student: {}", id);
    }
}
