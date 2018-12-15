package server.crm.modules.courseclass.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import server.crm.entities.Class;

import java.util.Date;
import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class, Long> {
    Page<Class> findAllByStatus(Pageable pageable, boolean status);
//
//    @Query("SELECT distinct (c) FROM classes c JOIN FETCH ALL PROPERTIES WHERE c.id = :id")
//    Optional<Class> findByIdEagly(@Param("id") Long id);

    Page<Class> findClassesByCourse_IdAndStatus(Pageable pageable, Long id, boolean status);

    @Query(value = "select c " +
            "from classes c " +
            "where " +
            "(upper(c.name) like upper(concat('%',:name, '%')) or " +
            "upper(c.description) like upper(concat('%',:descr,'%') )) and " +
            "c.course.id = :id and " +
            "c.status = :status")
    Page<Class> findClassesByNameOrDescriptionAndCourseId(Pageable pageable,
                                                          @Param("name") String keyWord,
                                                          @Param("descr") String keyWord2,
                                                          @Param("id") Long id,
                                                          @Param("status") boolean status);

    Class findByIdAndStatus(Long id, boolean status);

    @Query(value = "select count(c)" +
            "from classes c " +
            "where " +
            "c.openDate is not null and " +
            "c.openDate >= :fromDate and " +
            "c.openDate <= :toDate")
    int getNumberOfClassFromSpecificDate(@Param("fromDate") Date fromDate,
                                         @Param("toDate") Date toDate);

    @Query(value = "select count(c)" +
            "from classes c " +
            "where " +
            "c.endDate is not null and " +
            "c.endDate >= :fromDate and " +
            "c.endDate <= :toDate")
    int getNumberOfClassEndInSpecificDate(@Param("fromDate") Date fromDate,
                                          @Param("toDate") Date toDate);


}
