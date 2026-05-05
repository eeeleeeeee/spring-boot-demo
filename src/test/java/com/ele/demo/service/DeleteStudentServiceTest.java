package com.ele.demo.service;

import com.ele.demo.repository.Student;
import com.ele.demo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeleteStudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private FindStudentByIdService findStudentByIdService;

    private DeleteStudentService deleteStudentService;

    @BeforeEach
    void setUp() {
        deleteStudentService = new DeleteStudentService(studentRepository, findStudentByIdService);
    }

    @Test
    @DisplayName("delete calls repository deleteById when student exists")
    void execute_existingId_deletesStudent() {
        deleteStudentService.execute("abc123");

        verify(findStudentByIdService).execute("abc123");
        verify(studentRepository).deleteById("abc123");
    }

    @Test
    @DisplayName("delete throws RuntimeException when student not found")
    void execute_missingId_throwsRuntimeException() {
        doThrow(new RuntimeException("Student not found: not-exist"))
                .when(findStudentByIdService).execute("not-exist");

        assertThatThrownBy(() -> deleteStudentService.execute("not-exist"))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not-exist");
    }
}
