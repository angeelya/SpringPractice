package angeelya.spring.service;

import angeelya.spring.database.repository.StudentRepository;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.dto.response.StudentResponse;
import angeelya.spring.exceptionHandler.exception.DeleteException;
import angeelya.spring.exceptionHandler.exception.NoAddException;
import angeelya.spring.exceptionHandler.exception.NotFoundException;
import angeelya.spring.mapper.StudentMapper;
import angeelya.spring.service.util.TestStudentData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static angeelya.spring.service.util.TestGroupData.groupRequest;
import static angeelya.spring.service.util.TestStudentData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    StudentRepository studentRepository;
    @Mock
    StudentMapper studentMapper;
    @InjectMocks
    StudentService studentService;

    @Test
    void shouldFindAllStudents() throws NotFoundException {
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.studentsToStudentResponses(students)).thenReturn(studentResponses);
        List<StudentResponse> actual = studentService.findAllStudents();
        assertEquals(studentResponses, actual);
    }

    @Test
    void shouldFindAllStudentsThrowNotFoundException() {
        when(studentRepository.findAll()).thenReturn(studentsEmpty);
        assertThrows(NotFoundException.class, () -> studentService.findAllStudents());
    }

    @Test
    void shouldFindStudentByGroup() throws NotFoundException {
        when(studentRepository.findStudentsByGroup_Id(anyLong())).thenReturn(students);
        when(studentMapper.studentsToStudentResponses(students)).thenReturn(studentResponses);
        List<StudentResponse> actual = studentService.findStudentByGroup(groupRequest);
        assertEquals(studentResponses, actual);
    }

    @Test
    void shouldFindStudentByGroupThrowNotFoundException() {
        when(studentRepository.findStudentsByGroup_Id(anyLong())).thenReturn(studentsEmpty);
        assertThrows(NotFoundException.class, () -> studentService.findStudentByGroup(groupRequest));
    }

    @Test
    void shouldFindStudentById() throws NotFoundException {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.of(student));
        when(studentMapper.studentToStudentResponse(student)).thenReturn(studentResponse);
        StudentResponse actual = studentService.findStudentById(studentRequest);
        assertEquals(studentResponse, actual);
    }

    @Test
    void shouldFindStudentByIdThrowNotFoundException() {
        when(studentRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> studentService.findStudentById(studentRequest));
    }

    @Test
    void shouldFindStudentsByKey() throws NotFoundException {
        when(studentRepository.findStudentsByNameIsLikeOrLastNameIsLike(anyString(), anyString())).thenReturn(students);
        when(studentMapper.studentsToStudentResponses(students)).thenReturn(studentResponses);
        List<StudentResponse> actual = studentService.findStudentsByKey(searchRequest);
        assertEquals(studentResponses, actual);
    }

    @Test
    void shouldFindStudentsByKeyThrowNotFoundException() {
        when(studentRepository.findStudentsByNameIsLikeOrLastNameIsLike(anyString(), anyString())).thenReturn(studentsEmpty);
        assertThrows(NotFoundException.class, () -> studentService.findStudentsByKey(searchRequest));
    }

    @Test
    void shouldAddStudent() throws NoAddException {
        when(studentMapper.studentAddRequestToStudent(studentAddRequest)).thenReturn(studentAdd);
        when(studentRepository.save(studentAdd)).thenReturn(studentAdd);
        MessageResponse actual = studentService.addStudent(studentAddRequest);
        assertEquals(MS_SUCCESS_ADD,actual.getMessage());
    }
    @Test
    void shouldAddStudentThrowNoAddException() {
        when(studentMapper.studentAddRequestToStudent(studentAddRequest)).thenReturn(studentAdd);
        when(studentRepository.save(studentAdd)).thenReturn(studentEmpty);
        assertThrows(NoAddException.class,()->studentService.addStudent(studentAddRequest));
    }
    @Test
    void shouldUpdateStudent() throws NoAddException {
        when(studentMapper.studentUpdateRequestToStudent(studentUpdateRequest)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(student);
        MessageResponse actual =studentService.updateStudent(studentUpdateRequest);
        assertEquals(MS_SUCCESS_UPDATE,actual.getMessage());
    }
    @Test
    void shouldUpdateStudentThrowsNoAddException() {
        when(studentMapper.studentUpdateRequestToStudent(studentUpdateRequest)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(studentEmpty);
        assertThrows(NoAddException.class,()->studentService.updateStudent(studentUpdateRequest));
    }

    @Test
    void shouldDeleteStudent() throws DeleteException {
        doNothing().when(studentRepository).deleteById(anyLong());
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        MessageResponse actual = studentService.deleteStudent(studentRequest);
        assertEquals(TestStudentData.MS_SUCCESS_DELETE,actual.getMessage());
    }
    @Test
    void shouldDeleteStudentThrowDeleteException() {
        doNothing().when(studentRepository).deleteById(anyLong());
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        assertThrows(DeleteException.class,()->studentService.deleteStudent(studentRequest));
    }
}