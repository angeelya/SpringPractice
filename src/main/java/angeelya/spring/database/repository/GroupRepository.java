package angeelya.spring.database.repository;

import angeelya.spring.database.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    Optional<Group> findByGroupName(String groupName);
    List<Group> findGroupsByGroupNameIsLike(String groupName);
    @Transactional
    @Modifying
    @Query(value = "insert into teaching (group_id, teacher_id) values (:group_id, :teacher_id)", nativeQuery = true)
    void addTeaching(@Param("group_id") Long groupId, @Param("teacher_id") Long teacherId);
}
