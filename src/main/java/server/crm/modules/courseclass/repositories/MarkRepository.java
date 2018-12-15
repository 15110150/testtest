package server.crm.modules.courseclass.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.crm.entities.Mark;

/**
 * @author: khoa1
 * @create: 20/11/2018
 */
public interface MarkRepository extends JpaRepository<Mark, Long> {
}
