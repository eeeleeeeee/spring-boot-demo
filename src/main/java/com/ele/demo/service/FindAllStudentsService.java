package com.ele.demo.service;

import com.ele.demo.repository.Student;
import com.ele.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindAllStudentsService {

    private final StudentRepository studentRepository;

    public List<Student> execute() {
        log.info("Fetching all students");
        List<Student> students = studentRepository.findAll();
        log.info("Found {} students", students.size());
        return students;
    }
}
