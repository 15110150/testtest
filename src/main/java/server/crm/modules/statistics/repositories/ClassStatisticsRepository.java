package server.crm.modules.statistics.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import server.crm.entities.Class;

import java.util.Date;

/**
 * @author: khoa1
 * @create: 22/11/2018
 */
public interface ClassStatisticsRepository extends JpaRepository<Class, Long> {

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
