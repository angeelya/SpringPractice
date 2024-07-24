package angeelya.spring.dto.request;

import angeelya.spring.validation.GroupUnique;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GroupAddRequest {
    @NotNull(message = "Name should be not null")
    @GroupUnique
    @Size(min = 2, max = 250)
    private String name;

    public GroupAddRequest() {
    }

    public GroupAddRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

