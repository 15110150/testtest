package server.crm.modules.students.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import server.crm.entities.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAllByStatusIsTrue(Pageable pageresquest);

    Page<Student> findByNameContainingAndStatusIsTrueIgnoreCase(Pageable pageresquest, String name);
    @Query(value = "select s " +
            "from students s " +
            "where s.id not in :studentIds")
    List<Student> findAllStudentExceptStudentInAClass(@Param("studentIds") List<Long> studentIds);

}
