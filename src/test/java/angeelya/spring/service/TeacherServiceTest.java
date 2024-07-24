package angeelya.spring.service;

import angeelya.spring.repository.TeacherRepository;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.dto.response.TeacherResponse;
import angeelya.spring.exceptionHandler.exception.DeleteException;
import angeelya.spring.exceptionHandler.exception.NoAddException;
import angeelya.spring.exceptionHandler.exception.NotFoundException;
import angeelya.spring.mapper.TeacherMapper;
import angeelya.spring.service.util.TestTeacherData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static angeelya.spring.service.util.TestDisciplineData.disciplineRequest;
import static angeelya.spring.service.util.TestTeacherData.*;
import static angeelya.spring.service.util.TestTeacherData.MS_SUCCESS_ADD;
import static angeelya.spring.service.util.TestTeacherData.MS_SUCCESS_DELETE;
import static angeelya.spring.service.util.TestTeacherData.searchRequest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {
    @Mock
    TeacherRepository teacherRepository;
    @Mock
    TeacherMapper teacherMapper;
    @InjectMocks
    TeacherService teacherService;
    @Test
    void shouldAddTeacher() throws NoAddException {
        when(teacherMapper.teacherAddRequestToTeacher(teacherAddRequest)).thenReturn(teacherAdd);
        when(teacherRepository.save(teacherAdd)).thenReturn(teacherAdd);
        MessageResponse actual = teacherService.addTeacher(teacherAddRequest);
        assertEquals(MS_SUCCESS_ADD,actual.getMessage());
    }
    @Test
    void shouldAddTeacherThrowNoAddException() {
        when(teacherMapper.teacherAddRequestToTeacher(teacherAddRequest)).thenReturn(teacherAdd);
        when(teacherRepository.save(teacherAdd)).thenReturn(teacherEmpty);
        assertThrows(NoAddException.class,()->teacherService.addTeacher(teacherAddRequest));
    }
    @Test
    void shouldUpdateTeacher() throws NoAddException {
        when(teacherMapper.teacherUpdateRequestToTeacher(teacherUpdateRequest)).thenReturn(teacher);
        when(teacherRepository.save(teacher)).thenReturn(teacher);
        MessageResponse actual = teacherService.updateTeacher(teacherUpdateRequest);
        assertEquals(TestTeacherData.MS_SUCCESS_UPDATE,actual.getMessage());
    }
    @Test
    void shouldUpdateTeacherThrowNoAddException() {
        when(teacherMapper.teacherUpdateRequestToTeacher(teacherUpdateRequest)).thenReturn(teacher);
        when(teacherRepository.save(teacher)).thenReturn(teacherEmpty);
        assertThrows(NoAddException.class,()->teacherService.updateTeacher(teacherUpdateRequest));
    }
    @Test
    void shouldDeleteTeacher() throws DeleteException {
        doNothing().when(teacherRepository).deleteById(anyLong());
        when(teacherRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        MessageResponse actual = teacherService.deleteTeacher(teacherRequest);
        assertEquals(MS_SUCCESS_DELETE,actual.getMessage());
    }
    @Test
    void shouldDeleteTeacherThrowDeleteException() {
        doNothing().when(teacherRepository).deleteById(anyLong());
        when(teacherRepository.findById(any(Long.class))).thenReturn(Optional.of(teacher));
        assertThrows(DeleteException.class,()->teacherService.deleteTeacher(teacherRequest));
    }
    @Test
    void shouldFindAllTeachers() throws NotFoundException {
        when(teacherRepository.findAll()).thenReturn(teachers);
        when(teacherMapper.toTeacherResponses(teachers)).thenReturn(teacherResponses);
        List<TeacherResponse> actual = teacherService.findAllTeachers();
        assertEquals(teacherResponses, actual);
    }
    @Test
    void shouldFindAllTeachersThrowNotFoundException() {
        when(teacherRepository.findAll()).thenReturn(teachersEmpty);
        assertThrows(NotFoundException.class, () -> teacherService.findAllTeachers());
    }
    @Test
    void shouldFindTeachersByDisciplineId() throws NotFoundException {
        when(teacherRepository.findTeachersByDiscipline_Id(anyLong())).thenReturn(teachers);
        when(teacherMapper.toTeacherResponses(teachers)).thenReturn(teacherResponses);
        List<TeacherResponse> actual = teacherService.findTeachersByDisciplineId(disciplineRequest);
        assertEquals(teacherResponses, actual);
    }
    @Test
    void shouldFindTeachersByDisciplineIdThrowNotFoundException() {
        when(teacherRepository.findTeachersByDiscipline_Id(anyLong())).thenReturn(teachersEmpty);
        assertThrows(NotFoundException.class, () -> teacherService.findTeachersByDisciplineId(disciplineRequest));
    }
    @Test
    void shouldFindTeachersByKey() throws NotFoundException {
        when(teacherRepository.findTeachersByNameIsLikeOrLastNameIsLike(anyString(), anyString())).thenReturn(teachers);
        when(teacherMapper.toTeacherResponses(teachers)).thenReturn(teacherResponses);
        List<TeacherResponse> actual = teacherService.findTeachersByKey(searchRequest);
        assertEquals(teacherResponses, actual);
    }

    @Test
    void shouldFindTeachersByKeyThrowNotFoundException() {
        when(teacherRepository.findTeachersByNameIsLikeOrLastNameIsLike(anyString(), anyString())).thenReturn(teachersEmpty);
        assertThrows(NotFoundException.class, () -> teacherService.findTeachersByKey(searchRequest));
    }

    @Test
    void shouldFindTeacherById() throws NotFoundException {
        when(teacherRepository.findById(anyLong())).thenReturn(Optional.of(teacher));
        when(teacherMapper.toTeacherResponse(teacher)).thenReturn(teacherResponse);
        TeacherResponse actual = teacherService.findTeacherById(teacherRequest);
        assertEquals(teacherResponse, actual);
    }
    @Test
    void shouldFindTeacherByIdThrowNotFoundException() {
        when(teacherRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> teacherService.findTeacherById(teacherRequest));
    }
}