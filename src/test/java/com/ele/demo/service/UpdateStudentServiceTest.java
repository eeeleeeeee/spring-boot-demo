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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateStudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private FindStudentByIdService findStudentByIdService;

    private UpdateStudentService updateStudentService;

    @BeforeEach
    void setUp() {
        updateStudentService = new UpdateStudentService(studentRepository, findStudentByIdService);
    }

    @Test
    @DisplayName("update overwrites existing student fields and returns updated student")
    void execute_existingId_returnsUpdatedStudent() {
        Student existing = new Student();
        existing.setId("abc123");
        existing.setName("Alice");
        existing.setGpa(3.5);

        Student updated = new Student();
        updated.setName("Alice Updated");
        updated.setAge(23);
        updated.setMajor("Mathematics");
        updated.setGpa(3.9);
        updated.setDepartment("Science");

        when(findStudentByIdService.execute("abc123")).thenReturn(existing);
        when(studentRepository.save(existing)).thenReturn(existing);

        Student result = updateStudentService.execute("abc123", updated);

        assertThat(result.getName()).isEqualTo("Alice Updated");
        assertThat(result.getGpa()).isEqualTo(3.9);
        verify(findStudentByIdService).execute("abc123");
        verify(studentRepository).save(existing);
    }

    @Test
    @DisplayName("update throws RuntimeException when student not found")
    void execute_missingId_throwsRuntimeException() {
        when(findStudentByIdService.execute("not-exist"))
                .thenThrow(new RuntimeException("Student not found: not-exist"));

        assertThatThrownBy(() -> updateStudentService.execute("not-exist", new Student()))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("not-exist");
    }
}
