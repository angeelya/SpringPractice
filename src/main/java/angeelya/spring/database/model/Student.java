package angeelya.spring.database.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @JoinColumn(name = "group_id")
    @ManyToOne(optional = false)
    private Group group;

    public Student() {
    }

    public Student(Long id, String name, String lastName, Group group) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.group=group;
    }
    public Student( String name, String lastName, Group group) {
        this.name = name;
        this.lastName = lastName;
        this.group=group;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
