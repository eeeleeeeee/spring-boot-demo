package com.ele.demo.service;

import com.ele.demo.repository.Student;
import com.ele.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateStudentService {

    private final StudentRepository studentRepository;

    public Student execute(Student student) {
        log.info("Creating student: {}", student.getName());
        Student saved = studentRepository.save(student);
        log.info("Created student with id: {}", saved.getId());
        return saved;
    }
}
