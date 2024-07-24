package angeelya.spring.controller;

import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.dto.response.StudentResponse;
import angeelya.spring.exceptionHandler.exception.DeleteException;
import angeelya.spring.exceptionHandler.exception.NoAddException;
import angeelya.spring.exceptionHandler.exception.NotFoundException;
import angeelya.spring.exceptionHandler.exception.ValidationErrorsException;
import angeelya.spring.service.StudentService;
import angeelya.spring.validation.service.ValidationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

import static angeelya.spring.controller.util.TestGroupControllerData.groupRequest;
import static angeelya.spring.controller.util.TestStudentControllerData.*;
import static angeelya.spring.controller.util.TestStudentControllerData.MS_SUCCESS_ADD;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class StudentControllerTest {
    @Mock
    private StudentService studentService;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private ValidationService validationService;
    @InjectMocks
    private StudentController studentController;


    @Test
    void shouldGetAllStudents() throws NotFoundException {
        when(studentService.findAllStudents()).thenReturn(studentResponses);
        ResponseEntity<List<StudentResponse>> responseEntity = studentController.getAllStudents();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(studentResponse, responseEntity.getBody().get(0));
    }

    @ Test
    void shouldAddStudent() throws NoAddException, ValidationErrorsException {
        when(studentService.addStudent(studentAddRequest)).thenReturn(messageResponseAdd);
        ResponseEntity<MessageResponse> responseEntity = studentController.addStudent(studentAddRequest, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MS_SUCCESS_ADD, responseEntity.getBody().getMessage());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldUpdateStudent() throws NoAddException, ValidationErrorsException {
        when(studentService.updateStudent(studentUpdateRequest)).thenReturn(messageResponseUpdate);
        ResponseEntity<MessageResponse> responseEntity = studentController.updateStudent(studentUpdateRequest, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MS_SUCCESS_UPDATE, responseEntity.getBody().getMessage());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldGetStudentById() throws NotFoundException, ValidationErrorsException {
        when(studentService.findStudentById(studentRequest)).thenReturn(studentResponse);
        ResponseEntity<StudentResponse> responseEntity = studentController.getStudentById(studentRequest,bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(studentResponse, responseEntity.getBody());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldDeleteStudent() throws DeleteException, ValidationErrorsException {
        when(studentService.deleteStudent(studentRequest)).thenReturn(messageResponseDelete);
        ResponseEntity<MessageResponse> responseEntity = studentController.deleteStudent(studentRequest, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MS_SUCCESS_DELETE, responseEntity.getBody().getMessage());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldGetStudentsByGroupId() throws NotFoundException, ValidationErrorsException {
        when(studentService.findStudentByGroup(groupRequest)).thenReturn(studentResponses);
        ResponseEntity<List<StudentResponse>> responseEntity = studentController.getStudentsByGroupId(groupRequest,bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1,responseEntity.getBody().size());
        assertEquals(studentResponses, responseEntity.getBody());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldSearchStudent() throws NotFoundException, ValidationErrorsException {
        when(studentService.findStudentsByKey(searchRequest)).thenReturn(studentResponses);
        ResponseEntity<List<StudentResponse>> responseEntity = studentController.searchStudent(searchRequest,bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1,responseEntity.getBody().size());
        assertEquals(studentResponses, responseEntity.getBody());
        verify(validationService, times(1)).validation(bindingResult);
    }
}