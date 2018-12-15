package server.crm.modules.users.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import server.crm.entities.Role;
import server.crm.modules.users.api.v1.dto.RoleDto;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDto roleToRoleDto(Role role);
}
