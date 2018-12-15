package server.crm.modules.users.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.crm.entities.Role;

import java.util.Optional;


@Repository
@Transactional
public interface RoleDao extends JpaRepository<Role, Long> {
    /**
     * @author : trungntm
     * @date : 1/11/2018 10h41
     */
    Optional<Role> findRoleByRoleName(String roleName);

    /**
     * @author : trungntm
     * @date : 3/11/2018 15h56
     */
    Optional<Role> findRoleById(long id);

    /**
     * @author : trungntm
     * @date : 25/10/2018 18h06
     * @content : method findRoleOptionalNamePagingAndSorting define by @query to find role by name or get all role
     */
    @Query(value = "SELECT r FROM roles r WHERE r.roleName LIKE CONCAT('%',:name,'%') and r.status=true")
    Page<Role> findRoleOptionalNamePagingAndSorting(@Param("name") String name, Pageable pageable);
    /**
     * @author : trungntm
     * @date : 3/11/2018
     * */
}