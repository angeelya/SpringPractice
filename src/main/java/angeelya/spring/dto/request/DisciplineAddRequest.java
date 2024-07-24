package angeelya.spring.dto.request;

import angeelya.spring.validation.DisciplineUnique;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DisciplineAddRequest {
    @NotNull(message = "Discipline should be not null")
    @DisciplineUnique
    @Size(min = 2, max = 250)
    private String discipline;

    public DisciplineAddRequest() {
    }

    public DisciplineAddRequest(String discipline) {
        this.discipline = discipline;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
}
