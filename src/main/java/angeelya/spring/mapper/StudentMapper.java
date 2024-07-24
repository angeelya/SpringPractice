package angeelya.spring.mapper;

import angeelya.spring.dto.request.StudentAddRequest;
import angeelya.spring.dto.request.StudentUpdateRequest;
import angeelya.spring.dto.response.StudentResponse;
import angeelya.spring.database.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {
    @Mapping(target = "groupName", source = "group.groupName")
    StudentResponse studentToStudentResponse(Student student);
    List<StudentResponse> studentsToStudentResponses(List<Student> students);
    @Mapping(target = "group.id", source = "groupId")
    Student studentAddRequestToStudent(StudentAddRequest studentAddRequest);
    @Mapping(target = "group.id", source = "groupId")
    Student studentUpdateRequestToStudent(StudentUpdateRequest studentUpdateRequest);
}
