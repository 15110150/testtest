package server.crm.modules.users.services.roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.crm.entities.Role;
import server.crm.exceptions.CustomNotFoundException;
import server.crm.modules.users.api.v1.dto.RoleDto;
import server.crm.modules.users.api.v1.mapper.RoleMapper;
import server.crm.modules.users.dao.RoleDao;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public RoleDto mapperSingle(Role role) {
        return roleMapper.roleToRoleDto(role);
    }

    @Override
    public List<RoleDto> mapperArrays(List<Role> roles) {
        return roles.stream().map(roleMapper::roleToRoleDto).collect(Collectors.toList());
    }

    /**
     * @author : trungntm
     * @date : 25/10/2018 23h17
     * @content : retrieve role by optional name, if name is prisent will file by name, or else get all roles
     * default page is 0, size is 10, order by id asc
     */
    @Override
    public Page<RoleDto> retrieveRoleOptionalNamePagingAndSorting(Optional<String> name, Optional<Integer> page, Optional<Integer> size) {
        return roleDao
                .findRoleOptionalNamePagingAndSorting(
                        name.orElse(""),
                        new PageRequest(page.orElse(0), size.orElse(10), Sort.Direction.ASC, "id"))
                .map(roleMapper::roleToRoleDto);
    }

    @Override
    public Role retrieveRoleByRoleName(String roleName) {
        Optional<Role> roleOptional = roleDao.findRoleByRoleName(roleName);
        roleOptional.orElseThrow(() -> new CustomNotFoundException("Not found role " + roleName));
        return roleOptional.get();
    }

    @Override
    public Role retrieveRoleById(long id) {
        Optional<Role> roleOptional = roleDao.findRoleById(id);
        roleOptional.orElseThrow(() -> new CustomNotFoundException("Not found role by id " + id));
        return roleOptional.get();
    }

    @Override
    public Role saveRole(Role role) {
        return roleDao.save(role);
    }

    /**
     * @author : trungntm
     * @date : 3/11/2018 23h32
     */
    @Override
    public Role updateRole(Role role, String updateBy) {
        Optional<Role> roleOptional = roleDao.findRoleById(role.getId());

        roleOptional.orElseThrow(() -> new CustomNotFoundException("Not found role by id " + role.getId()));

        Role updatedRole = roleOptional.get();
        updatedRole.setRoleName(role.getRoleName());
        updatedRole.setStatus(role.isStatus());
        updatedRole.setUpdatedDate(new Date());
        updatedRole.setUpdatedBy(updateBy);

        return roleDao.save(updatedRole);
    }

    @Override
    public Role insertRole(Role role, String createBy) {
        Optional<Role> roleOptional = roleDao.findRoleByRoleName(role.getRoleName());
        if (roleOptional.isPresent()) {
            throw new IllegalArgumentException("Role is exist!");
        }
        Role newRole = new Role();
        newRole.setRoleName(role.getRoleName());
        newRole.setStatus(true);
        newRole.setCreatedDate(new Date());
        newRole.setCreatedBy(createBy);
        return roleDao.save(newRole);
    }

    @Override
    public Role deleteRole(long id, String updateBy) {
        Optional<Role> roleOptional = roleDao.findRoleById(id);
        roleOptional.orElseThrow(() -> new CustomNotFoundException("Not found role by id " + id));
        roleOptional.get().setStatus(false);
        return roleDao.save(roleOptional.get());
    }
}
