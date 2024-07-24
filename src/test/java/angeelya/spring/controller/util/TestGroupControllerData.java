package angeelya.spring.controller.util;

import angeelya.spring.dto.request.*;
import angeelya.spring.dto.response.GroupResponse;
import angeelya.spring.dto.response.MessageResponse;

import java.util.List;

public class TestGroupControllerData {
   public static final String MS_SUCCESS_ADD = "Group adding is successful";
   public static final String MS_SUCCESS_UPDATE = "Group updating is successful";
   public static final String MS_SUCCESS_DELETE = "Group deleting is successful";
   public static final String MS_SUCCESS_ADD_TEACHING = "Teaching adding is successful";

   public static GroupAddRequest groupAddRequest = new GroupAddRequest("Q-10");
   public static GroupUpdateRequest groupUpdateRequest = new GroupUpdateRequest(1L,"T-2");
   public static GroupRequest groupRequest = new GroupRequest(1L);

   public static List<GroupResponse> groupResponses = List.of(new GroupResponse(1L,"A-1"), new GroupResponse(2L,"B-2"));
   public static MessageResponse messageResponseAdd= new MessageResponse(MS_SUCCESS_ADD);
   public static MessageResponse messageResponseUpdate= new MessageResponse(MS_SUCCESS_UPDATE);
   public static MessageResponse messageResponseDelete= new MessageResponse(MS_SUCCESS_DELETE);
   public static MessageResponse messageResponseTeaching= new MessageResponse(MS_SUCCESS_ADD_TEACHING);
   public static SearchRequest searchRequest = new SearchRequest("-");
   public static GroupResponse groupResponse = new GroupResponse(1L,"A-1");
   public static TeachingRequest teachingRequest = new TeachingRequest(1L,2L);

}
