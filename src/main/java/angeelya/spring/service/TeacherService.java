package angeelya.spring.service;

import angeelya.spring.dto.request.*;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.dto.response.TeacherResponse;
import angeelya.spring.exceptionHandler.exception.DeleteException;
import angeelya.spring.exceptionHandler.exception.NoAddException;
import angeelya.spring.exceptionHandler.exception.NotFoundException;
import angeelya.spring.mapper.TeacherMapper;
import angeelya.spring.database.model.Teacher;
import angeelya.spring.database.repository.TeacherRepository;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TeacherService {
    private static final String MS_SUCCESS_ADD = "Teacher adding is successful";
    private static final String MS_FAILED_SAVE = "Failed to save teacher";
    private static final String MS_SUCCESS_UPDATE = "Teacher updating is successful";
    private static final String MS_FAILED_DELETE = "Failed to delete teacher";
    private static final String MS_SUCCESS_DELETE = "Teacher deleting is successful";
    private static final String MS_NOT_FOUND_LIST = "No teachers found";
    private static final String MS_NOT_FOUND = "Teacher not found";
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherMapper teacherMapper;

    public MessageResponse addTeacher(TeacherAddRequest teacherAddRequest) throws NoAddException {
        Teacher teacher = teacherMapper.teacherAddRequestToTeacher(teacherAddRequest);
        saveTeacher(teacher);
        return new MessageResponse(MS_SUCCESS_ADD);
    }

    public MessageResponse updateTeacher(TeacherUpdateRequest teacherUpdateRequest) throws NoAddException {
        Teacher teacher = teacherMapper.teacherUpdateRequestToTeacher(teacherUpdateRequest);
        saveTeacher(teacher);
        return new MessageResponse(MS_SUCCESS_UPDATE);
    }

    public MessageResponse deleteTeacher(TeacherRequest teacherRequest) throws DeleteException {
        delete(teacherRequest.getTeacherId());
        Optional<Teacher> teacher = teacherRepository.findById(teacherRequest.getTeacherId());
        if (teacher.isPresent()) throw new DeleteException(MS_FAILED_DELETE);
        return new MessageResponse(MS_SUCCESS_DELETE);
    }

    public List<TeacherResponse> findAllTeachers() throws NotFoundException {
        List<Teacher> teachers = teacherRepository.findAll();
        return getTeacherResponses(teachers);
    }

    public List<TeacherResponse> findTeachersByDisciplineId(DisciplineRequest disciplineRequest) throws NotFoundException {
        List<Teacher> teachers = teacherRepository.findTeachersByDiscipline_Id(disciplineRequest.getDisciplineId());
        return getTeacherResponses(teachers);
    }

    public List<TeacherResponse> findTeachersByKey(SearchRequest searchRequest) throws NotFoundException {
        String key = searchRequest.getKey();
        List<Teacher> teachers = teacherRepository.findTeachersByNameIsLikeOrLastNameIsLike("%" + key + "%", "%" + key + "%");
        return getTeacherResponses(teachers);
    }

    public TeacherResponse findTeacherById(TeacherRequest teacherRequest) throws NotFoundException {
        Optional<Teacher> teacher = teacherRepository.findById(teacherRequest.getTeacherId());
        return getTeacherResponse(teacher);
    }

    private TeacherResponse getTeacherResponse(Optional<Teacher> teacher) throws NotFoundException {
        if (teacher.isEmpty()) throw new NotFoundException(MS_NOT_FOUND);
        return teacherMapper.toTeacherResponse(teacher.get());
    }

    private List<TeacherResponse> getTeacherResponses(List<Teacher> teachers) throws NotFoundException {
        if (teachers.isEmpty()) throw new NotFoundException(MS_NOT_FOUND_LIST);
        return teacherMapper.toTeacherResponses(teachers);
    }

    private void delete(Long teacherId) throws DeleteException {
        try {
            teacherRepository.deleteById(teacherId);
        } catch (EmptyResultDataAccessException | TransactionRequiredException e) {
            throw new DeleteException(MS_FAILED_DELETE);
        }
    }

    private void saveTeacher(Teacher teacher) throws NoAddException {
        try {
            teacher = teacherRepository.save(teacher);
            if (teacher.getLastName() == null && teacher.getName() == null && teacher.getDiscipline() == null)
                throw new NoAddException(MS_FAILED_SAVE);
        } catch (DataAccessException e) {
            throw new NoAddException(MS_FAILED_SAVE);
        }
    }
}
