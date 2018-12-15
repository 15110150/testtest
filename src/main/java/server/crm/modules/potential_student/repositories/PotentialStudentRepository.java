package server.crm.modules.potential_student.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import server.crm.entities.PotentialStudent;

public interface PotentialStudentRepository extends JpaRepository<PotentialStudent, Long> {
    Page<PotentialStudent> findAllByStatusIsTrue(Pageable pageresquest);

    Page<PotentialStudent> findByNameContainingAndStatusIsTrueIgnoreCase(Pageable pageresquest, String name);
}
