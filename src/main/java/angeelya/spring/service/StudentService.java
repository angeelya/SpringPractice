package angeelya.spring.service;

import angeelya.spring.dto.request.*;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.dto.response.StudentResponse;
import angeelya.spring.exceptionHandler.exception.DeleteException;
import angeelya.spring.exceptionHandler.exception.NoAddException;
import angeelya.spring.exceptionHandler.exception.NotFoundException;
import angeelya.spring.mapper.StudentMapper;
import angeelya.spring.database.model.Student;
import angeelya.spring.database.repository.StudentRepository;
import jakarta.persistence.TransactionRequiredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private static final String
            MS_NOT_FOUND_LIST = "No students found";
    private static final String MS_NOT_FOUND = "Student not found";
    private static final String MS_FAILED_DELETE = "Failed to delete student";
    private static final String MS_FAILED_SAVE = "Failed to save student";
    private static final String MS_SUCCESS_ADD = "Student adding is successful";
    private static final String MS_SUCCESS_UPDATE = "Student updating is successful";
    private static final String MS_SUCCESS_DELETE = "Student deleting is successful";

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    public List<StudentResponse> findAllStudents() throws NotFoundException {
        List<Student> students = studentRepository.findAll();
        return getStudentResponses(students);
    }

    public List<StudentResponse> findStudentByGroup(GroupRequest groupRequest) throws NotFoundException {
        List<Student> students = studentRepository.findStudentsByGroup_Id(groupRequest.getGroupId());
        return getStudentResponses(students);
    }

    public StudentResponse findStudentById(StudentRequest studentRequest) throws NotFoundException {
        Optional<Student> student = studentRepository.findById(studentRequest.getStudentId());
        return getStudentResponse(student);
    }

    public List<StudentResponse> findStudentsByKey(SearchRequest searchRequest) throws NotFoundException {
        String key = searchRequest.getKey();
        List<Student> students = studentRepository.findStudentsByNameIsLikeOrLastNameIsLike("%" + key + "%", "%" + key + "%");
        return getStudentResponses(students);
    }

    public MessageResponse addStudent(StudentAddRequest studentAddRequest) throws NoAddException {
        Student student = studentMapper.studentAddRequestToStudent(studentAddRequest);
        saveStudent(student);
        return new MessageResponse(MS_SUCCESS_ADD);
    }

    public MessageResponse updateStudent(StudentUpdateRequest studentUpdateRequest) throws NoAddException {
        Student student = studentMapper.studentUpdateRequestToStudent(studentUpdateRequest);
        saveStudent(student);
        return new MessageResponse(MS_SUCCESS_UPDATE);
    }

    public MessageResponse deleteStudent(StudentRequest studentRequest) throws DeleteException {
        delete(studentRequest.getStudentId());
        Optional<Student> student = studentRepository.findById(studentRequest.getStudentId());
        if (student.isPresent()) throw new DeleteException(MS_FAILED_DELETE);
        return new MessageResponse(MS_SUCCESS_DELETE);
    }

    private void delete(Long studentId) throws DeleteException {
        try {
            studentRepository.deleteById(studentId);
        } catch (EmptyResultDataAccessException | TransactionRequiredException e) {
            throw new DeleteException(MS_FAILED_DELETE);
        }
    }

    private void saveStudent(Student student) throws NoAddException {
        try {
            student = studentRepository.save(student);
            if (student.getLastName() == null && student.getName() == null && student.getGroup() == null)
                throw new NoAddException(MS_FAILED_SAVE);
        } catch (DataAccessException e) {
            throw new NoAddException(MS_FAILED_SAVE);
        }
    }

    private StudentResponse getStudentResponse(Optional<Student> student) throws NotFoundException {
        if (student.isEmpty()) throw new NotFoundException(MS_NOT_FOUND);
        return studentMapper.studentToStudentResponse(student.get());
    }

    private List<StudentResponse> getStudentResponses(List<Student> students) throws NotFoundException {
        if (students.isEmpty()) throw new NotFoundException(MS_NOT_FOUND_LIST);
        return studentMapper.studentsToStudentResponses(students);
    }
}
