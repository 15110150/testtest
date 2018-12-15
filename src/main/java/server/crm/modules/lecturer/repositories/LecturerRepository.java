package server.crm.modules.lecturer.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import server.crm.entities.Lecturer;

public interface LecturerRepository extends JpaRepository<Lecturer, Long> {
    Page<Lecturer> findAllByStatusIsTrue(Pageable pageresquest);

    Page<Lecturer> findByNameContainingAndStatusIsTrueIgnoreCase(Pageable pageresquest, String name);
}
