package server.crm.modules.statistics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import server.crm.entities.Course;

import java.util.Date;
import java.util.List;

/**
 * @author: khoa1
 * @create: 22/11/2018
 */
public interface CourseStatisticRepository extends JpaRepository<Course, Long> {

    @Query(value = "select distinct c " +
            "from courses c join fetch c.classes " +
            "where " +
            "size(c.classes) > 0 and (" +
            "select count(cl) " +
            "from classes cl " +
            "where cl.course.id = c.id and cl.openDate >= :yearStartDay and cl.openDate <= :yearEndDay) > 0" +
            "")
    List<Course> getCourseHaveClassInYear(@Param("yearStartDay") Date yearStartDay, @Param("yearEndDay") Date yearEndDay);

}
