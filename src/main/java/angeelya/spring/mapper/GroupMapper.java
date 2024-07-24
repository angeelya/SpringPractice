package angeelya.spring.mapper;

import angeelya.spring.dto.request.GroupAddRequest;
import angeelya.spring.dto.request.GroupUpdateRequest;
import angeelya.spring.dto.response.GroupResponse;
import angeelya.spring.dto.response.StudentResponse;
import angeelya.spring.model.Group;
import angeelya.spring.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupMapper {

    @Mapping(target = "groupName", source = "name")
    Group groupAddRequestToGroup(GroupAddRequest groupAddRequest);
    @Mapping(target = "groupName", source = "name")
    Group groupUpdateRequestToGroup(GroupUpdateRequest groupUpdateRequest);

    @Mapping(target = "name", source = "groupName")
    @Mapping(target = "studentResponses", source = "students",qualifiedByName = "studentResponses")
    GroupResponse toGroupResponse(Group group);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "groupName", source = "group.groupName")
    StudentResponse toStudentResponse(Student student);
    @Named("studentResponses")
    default List<StudentResponse> studentResponses(List<Student> students) {
        return students != null ? students.stream().map(this::toStudentResponse).toList() : null;
    }
    List<GroupResponse> toGroupResponses(List<Group> groups);
}
