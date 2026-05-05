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
class FindAllStudentsServiceTest {

    @Mock
    private StudentRepository studentRepository;

    private FindAllStudentsService findAllStudentsService;

    @BeforeEach
    void setUp() {
        findAllStudentsService = new FindAllStudentsService(studentRepository);
    }

    @Test
    @DisplayName("findAll returns all students")
    void execute_returnsAllStudents() {
        Student alice = new Student();
        alice.setName("Alice");
        Student bob = new Student();
        bob.setName("Bob");
        when(studentRepository.findAll()).thenReturn(List.of(alice, bob));

        List<Student> result = findAllStudentsService.execute();

        assertThat(result).hasSize(2);
        verify(studentRepository).findAll();
    }

    @Test
    @DisplayName("findAll returns empty list when no students")
    void execute_noStudents_returnsEmptyList() {
        when(studentRepository.findAll()).thenReturn(List.of());

        List<Student> result = findAllStudentsService.execute();

        assertThat(result).isEmpty();
    }
}
