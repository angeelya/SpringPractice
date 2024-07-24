package angeelya.spring.controller.util;

import angeelya.spring.dto.request.DisciplineAddRequest;
import angeelya.spring.dto.request.DisciplineRequest;
import angeelya.spring.dto.request.DisciplineUpdateRequest;
import angeelya.spring.dto.request.SearchRequest;
import angeelya.spring.dto.response.DisciplineResponse;
import angeelya.spring.dto.response.MessageResponse;

import java.util.List;

import static angeelya.spring.service.util.TestDisciplineData.MS_SUCCESS_ADD;

public class TestDisciplineControllerData {
    public static final String MS_SUCCESS_ADD = "Discipline adding is successful";
    public static final String MS_SUCCESS_UPDATE = "Discipline updating is successful";
    public static List<DisciplineResponse> disciplineResponses = List.of(new DisciplineResponse(1L, "Math"), new DisciplineResponse(2L, "History"));
    public static DisciplineAddRequest disciplineAddRequest = new DisciplineAddRequest("Economy");
    public static DisciplineUpdateRequest disciplineUpdateRequest = new DisciplineUpdateRequest("Economy", 1L);

    public static MessageResponse messageResponseAdd = new MessageResponse(MS_SUCCESS_ADD);
    public static MessageResponse messageResponseUpdate = new MessageResponse(MS_SUCCESS_UPDATE);
    public static SearchRequest searchRequest = new SearchRequest("t");
    public static DisciplineRequest disciplineRequest = new DisciplineRequest(1L);


}
