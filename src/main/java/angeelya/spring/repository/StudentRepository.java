package angeelya.spring.repository;

import angeelya.spring.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findStudentsByGroup_Id(Long groupId);
    List<Student> findStudentsByNameIsLikeOrLastNameIsLike(String name, String lastName);

}