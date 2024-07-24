package angeelya.spring.dto.request;

import angeelya.spring.validation.NameContent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TeacherAddRequest {
    @NotNull(message = "Name should be not null")
    @Size(min = 2, max = 250)
    @NameContent
    private String name;
    @NotNull(message = "LastName should be not null")
    @Size(min = 2, max = 250)
    @NameContent
    private String lastName;
    @Min(value = 1,message = "DisciplineId should be more than 1")
    @NotNull(message ="DisciplineId should be not null")
    private Long disciplineId;

    public TeacherAddRequest(String name, String lastName, Long disciplineId) {
        this.name = name;
        this.lastName = lastName;
        this.disciplineId = disciplineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Long disciplineId) {
        this.disciplineId = disciplineId;
    }
}
