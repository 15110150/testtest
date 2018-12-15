package server.crm.modules.courseclass.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import server.crm.entities.Course;

import java.util.Date;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findAllByStatusIsTrue(Pageable pageresquest);

    Page<Course> findByNameContainingAndStatusIsTrueIgnoreCase(Pageable pageresquest, String name);

    Page<Course> findAllByCourseCategory_IdAndStatusIsTrue(Pageable pageresquest, Long catId);

    Page<Course> findAllByLongTermCourseAndStatusIsTrue(Pageable pageresquest, Boolean longTerm);

    @Query(value = "select c " +
            "from courses c " +
            "where " +
            "(upper(c.name) like upper(concat('%',:name, '%')) or " +
            "upper(c.description) like upper(concat('%',:descr,'%') )) and " +
            "c.courseCategory.id = :id and " +
            "c.status = :status and c.longTermCourse = :longTerm")
    Page<Course> findByCourseCategoryLongTermAndKeyWord(Pageable pageable,
                                                        @Param("status") boolean status,
                                                        @Param("id") Long id,
                                                        @Param("longTerm") boolean longTerm,
                                                        @Param("name") String keyWord,
                                                        @Param("descr") String keyWord2);

    @Query(value = "select c " +
            "from courses c " +
            "where " +
            "(upper(c.name) like upper(concat('%',:name, '%')) or " +
            "upper(c.description) like upper(concat('%',:descr,'%') )) and " +
            "c.status = :status and c.longTermCourse = :longTerm")
    Page<Course> findByLongTermAndKeyWord(Pageable pageable,
                                          @Param("status") boolean status,
                                          @Param("longTerm") boolean longTerm,
                                          @Param("name") String keyWord,
                                          @Param("descr") String keyWord2);


    @Query(value = "select c " +
            "from courses c " +
            "where " +
            "(upper(c.name) like upper(concat('%',:name, '%')) or " +
            "upper(c.description) like upper(concat('%',:descr,'%') )) and " +
            "c.courseCategory.id = :id and " +
            "c.status = :status")
    Page<Course> findByCourseCategoryAndKeyWord(Pageable pageable,
                                                @Param("status") boolean status,
                                                @Param("id") Long id,
                                                @Param("name") String keyWord,
                                                @Param("descr") String keyWord2);

    Page<Course> findAllByLongTermCourseAndCourseCategory_IdAndStatusIsTrue(Pageable pageresquest, boolean longTerm, Long catId);

    @Query(value = "select c " +
            "from courses c " +
            "where " +
            "(upper(c.name) like upper(concat('%',:name, '%')) or " +
            "upper(c.description) like upper(concat('%',:descr,'%') )) and " +
            "c.status = :status")
    Page<Course> findByKeyWord(Pageable pageable,
                               @Param("status") boolean status,
                               @Param("name") String keyWord,
                               @Param("descr") String keyWord2);

    @Query(value = "select count(c)" +
            "from courses c " +
            "where " +
            "c.createdDate is not null and " +
            "c.createdDate >= :fromDate ")
    int getNumberOfCourseCreatedFromSpecificDate(@Param("fromDate") Date fromDate);

}