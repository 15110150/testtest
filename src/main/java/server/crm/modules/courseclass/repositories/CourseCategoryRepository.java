package server.crm.modules.courseclass.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import server.crm.entities.CourseCategory;

import java.util.List;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {
    List<CourseCategory> findAllByStatus(boolean status);

    Page<CourseCategory> findAllByStatus(Pageable pageresquest, boolean status);

    Page<CourseCategory> findByNameContainingIgnoreCaseAndStatusIsTrue(Pageable pageresquest, String name);
}