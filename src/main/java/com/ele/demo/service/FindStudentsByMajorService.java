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
public class FindStudentsByMajorService {

    private final StudentRepository studentRepository;

    public List<Student> execute(String major) {
        log.info("Fetching students by major: {}", major);
        List<Student> students = studentRepository.findByMajor(major);
        log.info("Found {} students with major: {}", students.size(), major);
        return students;
    }
}
