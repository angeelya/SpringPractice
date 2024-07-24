package angeelya.spring.database.repository;

import angeelya.spring.database.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    List<Teacher> findTeachersByDiscipline_Id(Long disciplineId);
    List<Teacher> findTeachersByNameIsLikeOrLastNameIsLike(String name, String lastName);
}
