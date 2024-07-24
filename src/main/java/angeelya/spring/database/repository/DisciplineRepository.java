package angeelya.spring.database.repository;

import angeelya.spring.database.model.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DisciplineRepository extends JpaRepository<Discipline,Long> {
        Optional<Discipline> findDisciplineByDisciplineName(String disciplineName);
        List<Discipline> findDisciplinesByDisciplineNameIsLike(String disciplineName);
}
