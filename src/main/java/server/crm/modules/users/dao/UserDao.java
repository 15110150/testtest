package server.crm.modules.users.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import server.crm.entities.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserDao extends JpaRepository<User, Long> {
    /**
     * @author : trungntm
     * @date : 23/10/2018 23h22
     * @content : method findUserByUsername extends from Jpa
     */
    Optional<User> findUserByUsername(String s);

    /**
     * @author : trungntm
     * @date : 25/10/2018 13h17
     * @content : method findUserById extends from Jpa
     */
    Optional<User> findUserById(long id);

    /**
     * @author : trungntm
     * @date : 25/10/2018 18h06
     * @content : method findRoleOptionalNamePagingAndSorting define by @query to find role by name or get all role
     */
    @Query(value = "SELECT u FROM users u " +
            "INNER JOIN u.roles r " +
            "WHERE r.id = :id AND u.status = true AND u.username LIKE CONCAT('%', :keyword, '%')"
    )
    Page<User> findUserByRolePaging(@Param("id") Long id, @Param("keyword") String keyword, Pageable pageable);
}
