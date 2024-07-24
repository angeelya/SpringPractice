package angeelya.spring.database.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "group_university")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String groupName;
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<Student> students;
    @ManyToMany(mappedBy = "groups")
    private List<Teacher> teachers;

    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public Group(Long id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
