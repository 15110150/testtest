package server.crm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.crm.entities.User;

import java.util.Optional;

/**
 * @author : trungntm
 * @date : 23/10/2018 22h50
 * @content : change package server.crm.dao.UserDao -> server.crm.dao.CrmDao
 * */
@Repository
public interface CrmDao extends JpaRepository<User,Long> {
    /**
     * @author : trungntm
     * @date : 23/10/2018 22h50
     * @content : add method findUserByUsername to use spring security
     * */
    Optional<User> findUserByUsername(String username);
}
