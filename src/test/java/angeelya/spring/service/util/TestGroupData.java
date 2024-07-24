package angeelya.spring.service.util;

import angeelya.spring.model.Group;
import angeelya.spring.dto.request.*;
import angeelya.spring.dto.response.GroupResponse;

import java.util.List;

public class TestGroupData {
    public static final String MS_SUCCESS_ADD = "Group adding is successful";
    public static final String MS_SUCCESS_UPDATE = "Group updating is successful";
    public static final String MS_SUCCESS_DELETE = "Group deleting is successful";
    public static final String MS_SUCCESS_ADD_TEACHING = "Teaching adding is successful";

    public static GroupAddRequest groupAddRequest = new GroupAddRequest("G-12");
    public static GroupUpdateRequest groupUpdateRequest = new GroupUpdateRequest(1L,"E-2");
    public static Group groupAdd = new Group("G-12");
    public static Group groupEmpty = new Group();
    public static  Group group = new Group(1L,"E-23");
    public static TeachingRequest teachingRequest = new TeachingRequest(1L,2L);
    public static GroupResponse groupResponse= new GroupResponse(1L,"E-23");
    public static List<Group> groupsEmpty =List.of();
    public static List<Group> groups = List.of(group);
    public static List<GroupResponse> groupResponses = List.of(groupResponse);
    public static SearchRequest searchRequest = new SearchRequest("E-23");

    public static GroupRequest groupRequest = new GroupRequest(1L);
}
