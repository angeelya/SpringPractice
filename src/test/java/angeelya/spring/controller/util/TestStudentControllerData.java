package angeelya.spring.controller.util;

import angeelya.spring.dto.request.SearchRequest;
import angeelya.spring.dto.request.StudentAddRequest;
import angeelya.spring.dto.request.StudentRequest;
import angeelya.spring.dto.request.StudentUpdateRequest;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.dto.response.StudentResponse;

import java.util.List;

import static angeelya.spring.service.util.TestGroupData.group;

public class TestStudentControllerData {
    public static final String MS_SUCCESS_ADD = "Student adding is successful";
    public static final String MS_SUCCESS_UPDATE = "Student updating is successful";
    public static final String MS_SUCCESS_DELETE = "Student deleting is successful";
    public static MessageResponse messageResponseAdd= new MessageResponse(MS_SUCCESS_ADD);
    public static MessageResponse messageResponseUpdate= new MessageResponse(MS_SUCCESS_UPDATE);
    public static MessageResponse messageResponseDelete= new MessageResponse(MS_SUCCESS_DELETE);
    public static StudentRequest studentRequest = new StudentRequest(1L);
    public static StudentResponse studentResponse = new StudentResponse(1L, "Angelina", "Naidenova", group.getGroupName());
    public static List<StudentResponse> studentResponses = List.of(studentResponse);
    public static StudentAddRequest studentAddRequest = new StudentAddRequest("Angelina","Naidenova",1L);
    public static StudentUpdateRequest studentUpdateRequest = new StudentUpdateRequest(1L,"Angelina","Naidenova",1L);
    public static SearchRequest searchRequest = new SearchRequest("Angelina");
}
