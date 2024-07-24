package angeelya.spring.database.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @JoinColumn(name = "discipline_id")
    @ManyToOne(optional = false)
    private Discipline discipline;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "teaching",joinColumns = @JoinColumn(name = "teacher_id") ,
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups;

    public Teacher() {
    }

    public Teacher(String name, String lastName, Discipline discipline) {
        this.name = name;
        this.lastName = lastName;
        this.discipline = discipline;
    }

    public Teacher(Long id, String name, String lastName, Discipline discipline, List<Group> groups) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.discipline = discipline;
        this.groups = groups;
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

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
