package server.crm.modules.users.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import server.crm.entities.User;
import server.crm.modules.users.api.v1.dto.UserDto;

/**
 * @author : trungntm
 * @date : 23/11/2018 23h25
 * @content : add interface UserMapper, define method userToUserDto, convert User to UserDto
 */
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);
}
