package angeelya.spring.dto.request;

import angeelya.spring.validation.DisciplineUnique;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DisciplineUpdateRequest {
    @NotNull(message = "Discipline should be not null")
    @DisciplineUnique
    @Size(min = 2, max = 250)
    private String disciplineName;
    @Min(value = 1,message = "DisciplineId should be more than 1")
    @NotNull(message ="DisciplineId should be not null")
    private Long id;

    public DisciplineUpdateRequest() {
    }

    public DisciplineUpdateRequest(String disciplineName, Long id) {
        this.disciplineName = disciplineName;
        this.id = id;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
