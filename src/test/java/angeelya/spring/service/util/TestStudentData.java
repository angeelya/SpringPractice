package angeelya.spring.service.util;

import angeelya.spring.database.model.Student;
import angeelya.spring.dto.request.SearchRequest;
import angeelya.spring.dto.request.StudentAddRequest;
import angeelya.spring.dto.request.StudentRequest;
import angeelya.spring.dto.request.StudentUpdateRequest;
import angeelya.spring.dto.response.StudentResponse;

import java.util.List;

import static angeelya.spring.service.util.TestGroupData.group;
import static angeelya.spring.service.util.TestGroupData.searchRequest;

public class TestStudentData {
    public static final String MS_SUCCESS_ADD = "Student adding is successful";
    public static final String MS_SUCCESS_UPDATE = "Student updating is successful";
    public static final String MS_SUCCESS_DELETE = "Student deleting is successful";
    public static Student student = new Student(1L, "Angelina", "Naidenova", group);
    public static Student studentEmpty = new Student();
    public static Student studentAdd = new Student( "Angelina", "Naidenova", group);

    public static StudentResponse studentResponse = new StudentResponse(1L, "Angelina", "Naidenova", group.getGroupName());
    public static List<Student> students = List.of(student);
    public static List<Student> studentsEmpty = List.of();
    public static List<StudentResponse> studentResponses = List.of(studentResponse);
    public static StudentRequest studentRequest = new StudentRequest(1L);
    public static SearchRequest searchRequest = new SearchRequest("Angelina");
    public static StudentAddRequest studentAddRequest = new StudentAddRequest("Angelina","Naidenova",1L);
    public static StudentUpdateRequest studentUpdateRequest = new StudentUpdateRequest(1L,"Angelina","Naidenova",1L);
}
