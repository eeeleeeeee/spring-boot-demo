package com.ele.demo.service;

import com.ele.demo.repository.Student;
import com.ele.demo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindStudentByIdServiceTest {

    @Mock
    private StudentRepository studentRepository;

    private FindStudentByIdService findStudentByIdService;

    @BeforeEach
    void setUp() {
        findStudentByIdService = new FindStudentByIdService(studentRepository);
    }

    @Test
    @DisplayName("findById returns student when exists")
    void execute_existingId_returnsStudent() {
        Student student = new Student();
        student.setId("abc123");
        student.setName("Alice");
        when(studentRepository.findById("abc123")).thenReturn(Optional.of(student));

        Student result = findStudentByIdService.execute("abc123");

        assertThat(result.getName()).isEqualTo("Alice");
        verify(studentRepository).findById("abc123");
    }

    @Test
    @DisplayName("findById throws RuntimeException when not found")
    void execute_missingId_throwsRuntimeException() {
        when(studentRepository.findById("not-exist")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> findStudentByIdService.execute("not-exist"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not-exist");
    }
}
