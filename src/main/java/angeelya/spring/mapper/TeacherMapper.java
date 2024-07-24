package angeelya.spring.mapper;

import angeelya.spring.dto.request.TeacherAddRequest;
import angeelya.spring.dto.request.TeacherUpdateRequest;
import angeelya.spring.dto.response.GroupForTeacherResponse;
import angeelya.spring.dto.response.TeacherResponse;
import angeelya.spring.model.Group;
import angeelya.spring.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeacherMapper {
    @Mapping(target ="discipline.id" , source ="disciplineId")
    Teacher teacherAddRequestToTeacher(TeacherAddRequest teacherAddRequest);
    @Mapping(target = "discipline.id",source ="disciplineId")
    Teacher teacherUpdateRequestToTeacher(TeacherUpdateRequest teacherUpdateRequest);

    @Mapping(target = "disciplineName", source = "discipline.disciplineName")
    @Mapping(target = "groupForTeacherResponses", source = "groups",qualifiedByName = "groupToGroupForTeacherResponse")
    TeacherResponse toTeacherResponse(Teacher teacher);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "groupName")
    GroupForTeacherResponse toGroupForTeacherResponse(Group group);
    @Named("groupToGroupForTeacherResponse")
    default List<GroupForTeacherResponse> groupToGroupForTeacherResponse(List<Group> groups) {
        return groups != null ? groups.stream().map(this::toGroupForTeacherResponse).toList() : null;
    }
    List<TeacherResponse> toTeacherResponses(List<Teacher> teachers);
}
