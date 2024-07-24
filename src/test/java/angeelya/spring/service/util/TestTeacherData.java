package angeelya.spring.service.util;

import angeelya.spring.database.model.Student;
import angeelya.spring.database.model.Teacher;
import angeelya.spring.dto.request.*;
import angeelya.spring.dto.response.StudentResponse;
import angeelya.spring.dto.response.TeacherResponse;

import java.util.List;

import static angeelya.spring.service.util.TestDisciplineData.discipline;
import static angeelya.spring.service.util.TestGroupData.group;
import static angeelya.spring.service.util.TestGroupData.groups;
import static angeelya.spring.service.util.TestStudentData.student;

public class TestTeacherData {
    public static final String MS_SUCCESS_ADD = "Teacher adding is successful";
    public static final String MS_SUCCESS_UPDATE = "Teacher updating is successful";
    public static final String MS_SUCCESS_DELETE = "Teacher deleting is successful";
    public static Teacher teacher = new Teacher(1L, "Elizaveta", "Ivanova",discipline, groups );
    public static Teacher teacherEmpty = new Teacher();
    public static Teacher teacherAdd = new Teacher( "Elizaveta", "Ivanova",discipline);

    public static TeacherResponse teacherResponse = new TeacherResponse(1L, "Elizaveta", "Ivanova", discipline.getDisciplineName());
    public static List<Teacher> teachers = List.of(teacher);
    public static List<Teacher> teachersEmpty = List.of();
    public static List<TeacherResponse> teacherResponses = List.of(teacherResponse);
    public static TeacherRequest teacherRequest = new TeacherRequest(1L);
    public static SearchRequest searchRequest = new SearchRequest("Elizaveta");
    public static TeacherAddRequest teacherAddRequest = new TeacherAddRequest("Elizaveta", "Ivanova",1L);
    public static TeacherUpdateRequest teacherUpdateRequest = new TeacherUpdateRequest(1L,"Elizaveta", "Ivanova",1L);
}
