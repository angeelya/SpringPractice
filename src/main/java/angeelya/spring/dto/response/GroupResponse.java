package angeelya.spring.dto.response;

import java.util.List;

public class GroupResponse {
    private Long id;
    private String name;
    private List<StudentResponse> studentResponses;

    public GroupResponse() {
    }

    public GroupResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<StudentResponse> getStudentResponses() {
        return studentResponses;
    }

    public void setStudentResponses(List<StudentResponse> studentResponses) {
        this.studentResponses = studentResponses;
    }
}
