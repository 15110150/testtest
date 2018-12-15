package server.crm.modules.users.services.roles;

import org.springframework.data.domain.Page;
import server.crm.entities.Role;
import server.crm.modules.users.api.v1.dto.RoleDto;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    /**
     * @author : trungntm
     * @date : 3/11/2018 23h54
     */
    RoleDto mapperSingle(Role role);

    /**
     * @author : trungntm
     * @date : 4/11/2018 1h26
     */
    List<RoleDto> mapperArrays(List<Role> roles);

    /**
     * @author : trungntm
     * @date : 25/10/2018 18h07
     * @content : declare method retrieveAllRole to get all roles
     */
    Page<RoleDto> retrieveRoleOptionalNamePagingAndSorting(Optional<String> name, Optional<Integer> page, Optional<Integer> size);

    /**
     * @author : trungntm
     * @date : 1/11/2018 22h45
     */
    Role retrieveRoleByRoleName(String roleName);

    /**
     * @author : trungntm
     * @date : 3/11/2018 15h57
     */
    Role retrieveRoleById(long id);

    /**
     * @author : trungntm
     * @date : 1/11/2018 22h58
     */
    Role saveRole(Role role);

    /**
     * @author : trungntm
     * @date : 3/11/2018 23h32
     */
    Role updateRole(Role role, String updateBy);

    /**
     * @author : trungntm
     * @date : 3/11/2018 24h44
     */
    Role insertRole(Role role, String createBy);

    /**
     * @author : trungntm
     * @date : 4/11/2018 1h05
     */
    Role deleteRole(long id, String updateBy);
}
