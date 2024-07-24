package angeelya.spring.service;

import angeelya.spring.database.model.Discipline;
import angeelya.spring.database.repository.DisciplineRepository;
import angeelya.spring.dto.response.DisciplineResponse;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.exceptionHandler.exception.NoAddException;
import angeelya.spring.exceptionHandler.exception.NotFoundException;
import angeelya.spring.mapper.DisciplineMapper;
import angeelya.spring.service.util.TestDisciplineData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static angeelya.spring.service.util.TestDisciplineData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DisciplineServiceTest {
    @Mock
    DisciplineRepository disciplineRepository;
    @Mock
    DisciplineMapper disciplineMapper;
    @InjectMocks
    DisciplineService disciplineService;


    @Test
    void shouldAddDiscipline() throws NoAddException {
        when(disciplineMapper.disciplineAddRequestToDiscipline(disciplineAddRequest)).thenReturn(discipline);
        when(disciplineRepository.save(any(Discipline.class))).thenReturn(discipline);
        MessageResponse actual = disciplineService.addDiscipline(disciplineAddRequest);
        assertEquals(MS_SUCCESS_ADD, actual.getMessage());
    }

    @Test
    void shouldAddDisciplineThrowNoAddException() {
        when(disciplineMapper.disciplineAddRequestToDiscipline(disciplineAddRequest)).thenReturn(discipline);
        when(disciplineRepository.save(any(Discipline.class))).thenReturn(disciplineEmpty);
        assertThrows(NoAddException.class, () -> disciplineService.addDiscipline(disciplineAddRequest));
    }

    @Test
    void shouldUpdateDiscipline() throws NoAddException {
        when(disciplineMapper.disciplineUpdateRequestToDiscipline(disciplineUpdateRequest)).thenReturn(disciplineUpdate);
        when(disciplineRepository.save(any(Discipline.class))).thenReturn(disciplineUpdate);
        MessageResponse actual = disciplineService.updateDiscipline(disciplineUpdateRequest);
        assertEquals(TestDisciplineData.MS_SUCCESS_UPDATE, actual.getMessage());
    }

    @Test
    void shouldUpdateDisciplineThrowNoAddException() {
        when(disciplineMapper.disciplineUpdateRequestToDiscipline(disciplineUpdateRequest)).thenReturn(disciplineUpdate);
        when(disciplineRepository.save(any(Discipline.class))).thenReturn(disciplineEmpty);
        assertThrows(NoAddException.class, () -> disciplineService.updateDiscipline(disciplineUpdateRequest));
    }

    @Test
    void shouldFindDisciplineAll() throws NotFoundException {
        when(disciplineRepository.findAll()).thenReturn(disciplines);
        when(disciplineMapper.toDisciplineResponses(disciplines)).thenReturn(disciplineResponses);
        List<DisciplineResponse> actual = disciplineService.findDisciplineAll();
        assertEquals(disciplineResponses, actual);
    }

    @Test
    void shouldFindDisciplineAllThrowNotFoundException() {
        when(disciplineRepository.findAll()).thenReturn(disciplinesEmpty);
        assertThrows(NotFoundException.class, () -> disciplineService.findDisciplineAll());
    }

    @Test
    void shouldFindDisciplineKey() throws NotFoundException {
        when(disciplineRepository.findDisciplinesByDisciplineNameIsLike(any(String.class))).thenReturn(disciplines);
        when(disciplineMapper.toDisciplineResponses(disciplines)).thenReturn(disciplineResponses);
        List<DisciplineResponse> actual = disciplineService.findDisciplineKey(searchRequest);
        assertEquals(disciplineResponses, actual);
    }

    @Test
    void shouldFindDisciplineKeyThrowNotFoundException() {
        when(disciplineRepository.findDisciplinesByDisciplineNameIsLike(any(String.class))).thenReturn(disciplinesEmpty);
        assertThrows(NotFoundException.class, () -> disciplineService.findDisciplineKey(searchRequest));
    }
}