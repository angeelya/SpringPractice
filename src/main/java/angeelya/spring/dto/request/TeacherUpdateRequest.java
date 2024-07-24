package angeelya.spring.dto.request;

import angeelya.spring.validation.NameContent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TeacherUpdateRequest {
    @Min(value = 1,message = "Id should be more than 1")
    @NotNull(message ="Id should be not null")
    private Long id;
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

    public TeacherUpdateRequest(Long id, String name, String lastName, Long disciplineId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.disciplineId = disciplineId;
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
