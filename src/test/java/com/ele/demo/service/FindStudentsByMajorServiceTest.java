package com.ele.demo.service;

import com.ele.demo.repository.Student;
import com.ele.demo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindStudentsByMajorServiceTest {

    @Mock
    private StudentRepository studentRepository;

    private FindStudentsByMajorService findStudentsByMajorService;

    @BeforeEach
    void setUp() {
        findStudentsByMajorService = new FindStudentsByMajorService(studentRepository);
    }

    @Test
    @DisplayName("findByMajor returns matching students")
    void execute_existingMajor_returnsStudents() {
        Student alice = new Student();
        alice.setName("Alice");
        alice.setMajor("Computer Science");
        when(studentRepository.findByMajor("Computer Science")).thenReturn(List.of(alice));

        List<Student> result = findStudentsByMajorService.execute("Computer Science");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getMajor()).isEqualTo("Computer Science");
        verify(studentRepository).findByMajor("Computer Science");
    }

    @Test
    @DisplayName("findByMajor returns empty list when no match")
    void execute_noMatch_returnsEmptyList() {
        when(studentRepository.findByMajor("Physics")).thenReturn(List.of());

        List<Student> result = findStudentsByMajorService.execute("Physics");

        assertThat(result).isEmpty();
    }
}
