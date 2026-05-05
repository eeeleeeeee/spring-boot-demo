package com.ele.demo.service;

import com.ele.demo.repository.Student;
import com.ele.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateStudentService {

    private final StudentRepository studentRepository;
    private final FindStudentByIdService findStudentByIdService;

    public Student execute(String id, Student updated) {
        log.info("Updating student: {}", id);
        Student existing = findStudentByIdService.execute(id);
        existing.setName(updated.getName());
        existing.setAge(updated.getAge());
        existing.setMajor(updated.getMajor());
        existing.setGpa(updated.getGpa());
        existing.setDepartment(updated.getDepartment());
        Student saved = studentRepository.save(existing);
        log.info("Updated student: {}", id);
        return saved;
    }
}
