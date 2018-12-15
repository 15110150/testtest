package server.crm.modules.users.services.users;

import org.springframework.data.domain.Page;
import server.crm.entities.User;
import server.crm.modules.users.api.v1.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * @author : trungntm
     * @date : 23/10/2018 23h24
     */
    UserDto mapperSingle(User user);

    List<UserDto> mapperArrays(List<User> users);

    User retrieveUserByUsername(String s);

    /**
     * @author: trungntm
     * @date: 25/10/2018 12h43
     * @content : service Get list user
     */
    List<User> retrieveAllUser();

    /**
     * @author: trungntm
     * @date: 25/10/2018 13h18
     * @contet : service Get user by id
     */
    User retrieveUserById(long id);

    /**
     * @author : trungntm
     * @date : 3/11/2018 15h17
     */
    User save(User user);

    /**
     * @author : trungntm
     * @date : 8/11/2018 15h17
     */
    Page<User> retrieveUserByRolePaging(Long roleId, Optional<String> keyword, Optional<Integer> page, Optional<Integer> size);
}
