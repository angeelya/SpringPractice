package angeelya.spring.controller;

import angeelya.spring.dto.response.DisciplineResponse;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.exceptionHandler.exception.NoAddException;
import angeelya.spring.exceptionHandler.exception.NotFoundException;
import angeelya.spring.exceptionHandler.exception.ValidationErrorsException;
import angeelya.spring.service.DisciplineService;
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

import static angeelya.spring.controller.util.TestDisciplineControllerData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DisciplineControllerTest {
    @Mock
    private DisciplineService disciplineService;
    @Mock
    private ValidationService validationService;

    @Mock
    private BindingResult bindingResult;
    @InjectMocks
    private DisciplineController disciplineController;

    @Test
    void testGetAllDisciplines() throws NotFoundException {
        when(disciplineService.findDisciplineAll()).thenReturn(disciplineResponses);
        ResponseEntity<List<DisciplineResponse>> responseEntity = disciplineController.getAllDisciplines();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        assertEquals("Math", responseEntity.getBody().get(0).getDiscipline());
        assertEquals("History", responseEntity.getBody().get(1).getDiscipline());
    }

    @Test
    void shouldAddDiscipline() throws NoAddException, ValidationErrorsException {
        when(disciplineService.addDiscipline(disciplineAddRequest)).thenReturn(messageResponseAdd);
        ResponseEntity<MessageResponse> responseEntity = disciplineController.addDiscipline(disciplineAddRequest,bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MS_SUCCESS_ADD, responseEntity.getBody().getMessage());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldUpdateDiscipline() throws NoAddException, ValidationErrorsException {
        when(disciplineService.updateDiscipline(disciplineUpdateRequest)).thenReturn(messageResponseUpdate);
        ResponseEntity<MessageResponse> responseEntity = disciplineController.updateDiscipline(disciplineUpdateRequest,bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MS_SUCCESS_UPDATE, responseEntity.getBody().getMessage());
        verify(validationService, times(1)).validation(bindingResult);
    }

    @Test
    void shouldSearchDiscipline() throws NotFoundException, ValidationErrorsException {
        when(disciplineService.findDisciplineKey(searchRequest)).thenReturn(disciplineResponses);
        ResponseEntity<List<DisciplineResponse>> responseEntity = disciplineController.searchDiscipline(searchRequest, bindingResult);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(2, responseEntity.getBody().size());
        assertEquals("Math", responseEntity.getBody().get(0).getDiscipline());
        assertEquals("History", responseEntity.getBody().get(1).getDiscipline());
        verify(validationService, times(1)).validation(bindingResult);
    }
}