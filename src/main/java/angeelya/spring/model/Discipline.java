package angeelya.spring.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table( name ="discipline")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String disciplineName;
    @OneToMany(mappedBy = "discipline",cascade = CascadeType.ALL)
    List<Teacher> teachers;

    public Discipline() {
    }

    public Discipline(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public Discipline(Long id, String disciplineName) {
        this.id = id;
        this.disciplineName = disciplineName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
