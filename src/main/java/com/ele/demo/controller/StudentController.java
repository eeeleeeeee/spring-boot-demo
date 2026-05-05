package com.ele.demo.controller;

import com.ele.demo.repository.Student;
import com.ele.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final FindAllStudentsService findAllStudentsService;

    @GetMapping
    public List<Student> findAll() {
        return findAllStudentsService.execute();
    }

    private final FindStudentByIdService findStudentByIdService;

    @GetMapping("/{id}")
    public Student findById(@PathVariable String id) {
        return findStudentByIdService.execute(id);
    }

    private final FindStudentsByMajorService findStudentsByMajorService;

    @GetMapping("/major/{major}")
    public List<Student> findByMajor(@PathVariable String major) {
        return findStudentsByMajorService.execute(major);
    }

    private final CreateStudentService createStudentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student create(@RequestBody Student student) {
        return createStudentService.execute(student);
    }

    private final UpdateStudentService updateStudentService;

    @PutMapping("/{id}")
    public Student update(@PathVariable String id, @RequestBody Student student) {
        return updateStudentService.execute(id, student);
    }

    private final DeleteStudentService deleteStudentService;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        deleteStudentService.execute(id);
    }
}
