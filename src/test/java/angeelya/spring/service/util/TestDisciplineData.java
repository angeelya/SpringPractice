package angeelya.spring.service.util;

import angeelya.spring.model.Discipline;
import angeelya.spring.dto.request.DisciplineAddRequest;
import angeelya.spring.dto.request.DisciplineRequest;
import angeelya.spring.dto.request.DisciplineUpdateRequest;
import angeelya.spring.dto.request.SearchRequest;
import angeelya.spring.dto.response.DisciplineResponse;

import java.util.List;

public class TestDisciplineData {
    public static final String MS_SUCCESS_ADD = "Discipline adding is successful";
    public static final String MS_SUCCESS_UPDATE = "Discipline updating is successful";

    public static  DisciplineAddRequest disciplineAddRequest = new DisciplineAddRequest("Math");
    public static DisciplineUpdateRequest disciplineUpdateRequest = new DisciplineUpdateRequest("Math",1L);
    public  static Discipline discipline = new Discipline("Math");
    public static Discipline disciplineUpdate = new Discipline(1L,"Math");
    public static Discipline disciplineEmpty = new Discipline();
    public static Discipline elementListDiscipline = new Discipline(2L,"Economy");
    public static DisciplineResponse elementListDisciplineResponse = new DisciplineResponse(2L,"Economy");
    public static List<Discipline> disciplines = List.of(elementListDiscipline);
    public static List<Discipline> disciplinesEmpty = List.of();
    public static List<DisciplineResponse> disciplineResponses = List.of(elementListDisciplineResponse);
    public static SearchRequest searchRequest = new SearchRequest("Economy");
    public static DisciplineRequest disciplineRequest = new DisciplineRequest(1L);
}
