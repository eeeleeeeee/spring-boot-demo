package com.ele.demo.service;

import com.ele.demo.repository.Student;
import com.ele.demo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateStudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    private CreateStudentService createStudentService;

    @BeforeEach
    void setUp() {
        createStudentService = new CreateStudentService(studentRepository);
    }

    @Test
    @DisplayName("create saves student and returns it with id")
    void execute_validStudent_returnsSavedStudentWithId() {
        Student student = new Student();
        student.setName("Eve");
        student.setMajor("Computer Science");

        Student saved = new Student();
        saved.setId("abc123");
        saved.setName("Eve");
        saved.setMajor("Computer Science");

        when(studentRepository.save(student)).thenReturn(saved);

        Student result = createStudentService.execute(student);

        assertThat(result.getId()).isEqualTo("abc123");
        assertThat(result.getName()).isEqualTo("Eve");
        verify(studentRepository).save(student);
    }
}
