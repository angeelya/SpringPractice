package angeelya.spring.controller.util;

import angeelya.spring.dto.request.SearchRequest;
import angeelya.spring.dto.request.TeacherAddRequest;
import angeelya.spring.dto.request.TeacherRequest;
import angeelya.spring.dto.request.TeacherUpdateRequest;
import angeelya.spring.dto.response.MessageResponse;
import angeelya.spring.dto.response.TeacherResponse;

import java.util.List;

public class TestTeacherControllerData {
    public static final String MS_SUCCESS_ADD = "Teacher adding is successful";
    public static final String MS_SUCCESS_UPDATE = "Teacher updating is successful";
    public static final String MS_SUCCESS_DELETE = "Teacher deleting is successful";
    public static MessageResponse messageResponseAdd= new MessageResponse(MS_SUCCESS_ADD);
    public static MessageResponse messageResponseUpdate= new MessageResponse(MS_SUCCESS_UPDATE);
    public static MessageResponse messageResponseDelete= new MessageResponse(MS_SUCCESS_DELETE);
    public static TeacherRequest teacherRequest = new TeacherRequest(1L);
    public static SearchRequest searchRequest = new SearchRequest("Elizaveta");

    public static TeacherResponse teacherResponse = new TeacherResponse(1L, "Elizaveta", "Ivanova", "Economy");
    public static List<TeacherResponse> teacherResponses = List.of(teacherResponse);
    public static TeacherAddRequest teacherAddRequest = new TeacherAddRequest("Elizaveta", "Ivanova",1L);
    public static TeacherUpdateRequest teacherUpdateRequest = new TeacherUpdateRequest(1L,"Elizaveta", "Ivanova",1L);
}
