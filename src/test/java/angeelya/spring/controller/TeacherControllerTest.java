package angeelya.spring.controller;

import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.dto.response.TeacherResponse;
import angeelya.spring.exceptionHandler.exception.DeleteException;
import angeelya.spring.exceptionHandler.exception.NoAddException;
import angeelya.spring.exceptionHandler.exception.NotFoundException;
import angeelya.spring.exceptionHandler.exception.ValidationErrorsException;
import angeelya.spring.service.TeacherService;
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

import static angeelya.spring.controller.util.TestDisciplineControllerData.disciplineRequest;
import static angeelya.spring.controller.util.TestTeacherControllerData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherControllerTest {
    @Mock
    private TeacherService teacherService;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private ValidationService validationService;
    @InjectMocks
    private TeacherController teacherController;

    @Test
    void shouldGetAllTeachers() throws NotFoundException {
        when(teacherService.findAllTeachers()).thenReturn(teacherResponses);
        ResponseEntity<List<TeacherResponse>> responseEntity = teacherController.getAllTeachers();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(teacherResponse, responseEntity.getBody().get(0));
    }

    @Test
    void shouldAddTeacher() throws NoAddException, ValidationErrorsException {
        when(teacherService.addTeacher(teacherAddRequest)).thenReturn(messageResponseAdd);
        ResponseEntity<MessageResponse> responseEntity = teacherController.addTeacher(teacherAddRequest, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MS_SUCCESS_ADD, responseEntity.getBody().getMessage());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldUpdateTeacher() throws NoAddException, ValidationErrorsException {
        when(teacherService.updateTeacher(teacherUpdateRequest)).thenReturn(messageResponseUpdate);
        ResponseEntity<MessageResponse> responseEntity = teacherController.updateTeacher(teacherUpdateRequest, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MS_SUCCESS_UPDATE, responseEntity.getBody().getMessage());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void deleteTeacher() throws DeleteException, ValidationErrorsException {
        when(teacherService.deleteTeacher(teacherRequest)).thenReturn(messageResponseDelete);
        ResponseEntity<MessageResponse> responseEntity = teacherController.deleteTeacher(teacherRequest, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MS_SUCCESS_DELETE, responseEntity.getBody().getMessage());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldGetTeacherById() throws NotFoundException, ValidationErrorsException {
        when(teacherService.findTeacherById(teacherRequest)).thenReturn(teacherResponse);
        ResponseEntity<TeacherResponse> responseEntity = teacherController.getTeacherById(teacherRequest,bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(teacherResponse, responseEntity.getBody());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldGetTeacherByDiscipline() throws NotFoundException, ValidationErrorsException {
        when(teacherService.findTeachersByDisciplineId(disciplineRequest)).thenReturn(teacherResponses);
        ResponseEntity<List<TeacherResponse>> responseEntity = teacherController.getTeacherByDiscipline(disciplineRequest,bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(teacherResponses, responseEntity.getBody());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldSearchTeacher() throws NotFoundException, ValidationErrorsException {
        when(teacherService.findTeachersByKey(searchRequest)).thenReturn(teacherResponses);
        ResponseEntity<List<TeacherResponse>> responseEntity = teacherController.searchTeacher(searchRequest,bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().size());
        assertEquals(teacherResponses, responseEntity.getBody());
        verify(validationService, times(1)).validation(bindingResult);
    }
}