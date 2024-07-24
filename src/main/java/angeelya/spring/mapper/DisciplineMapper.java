package angeelya.spring.mapper;

import angeelya.spring.dto.request.DisciplineAddRequest;
import angeelya.spring.dto.request.DisciplineUpdateRequest;
import angeelya.spring.dto.response.DisciplineResponse;
import angeelya.spring.dto.response.TeacherForDisciplineResponse;
import angeelya.spring.model.Discipline;
import angeelya.spring.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DisciplineMapper {
    @Mapping(target = "disciplineName", source = "discipline")
    Discipline disciplineAddRequestToDiscipline(DisciplineAddRequest disciplineAddRequest);
    @Mapping(target = "disciplineName", source = "disciplineName")
    Discipline disciplineUpdateRequestToDiscipline(DisciplineUpdateRequest disciplineUpdateRequest);
    @Mapping(target = "discipline", source = "disciplineName")
    @Mapping(target = "teacherDisciplineResponses", source = "teachers",qualifiedByName = "teacherForDisciplineResponses")
    DisciplineResponse toDisciplineResponse(Discipline discipline);
    TeacherForDisciplineResponse toTeacherResponse(Teacher teacher);
    @Named("teacherForDisciplineResponses")
    default List<TeacherForDisciplineResponse> teacherForDisciplineResponses(List<Teacher> teachers) {
        return teachers != null ? teachers.stream().map(this::toTeacherResponse).toList() : null;
    }
    List<DisciplineResponse> toDisciplineResponses(List<Discipline> disciplines);
}
