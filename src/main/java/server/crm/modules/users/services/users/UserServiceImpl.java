package server.crm.modules.users.services.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.crm.entities.User;
import server.crm.exceptions.CustomNotFoundException;
import server.crm.modules.users.api.v1.dto.UserDto;
import server.crm.modules.users.api.v1.mapper.UserMapper;
import server.crm.modules.users.dao.UserDao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    /**
     * @author : trungntm
     * @date : 23/10/2018 23h30
     * @content : inject UserDao and UserMapper
     **/
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto mapperSingle(User user) {
        return userMapper.userToUserDto(user);
    }

    @Override
    public List<UserDto> mapperArrays(List<User> users) {
        return users.stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    /**
     * @author : trungntm
     * @date : 23/10/2018 23h31
     * @content : override method retrieveUserByUsername in UserService
     */
    @Override
    public User retrieveUserByUsername(String s) {
        Optional<User> userOptional = userDao.findUserByUsername(s);
        userOptional.orElseThrow(() -> new CustomNotFoundException("Not found username : " + s));
        return userOptional.get();
    }

    /**
     * @author: trungntm
     * @date: 25/10/2018 12h43
     * @contet : service implement Get list user, return type List<UserDto>
     */
    @Override
    public List<User> retrieveAllUser() {
        List<User> users = userDao.findAll();
        if (users.isEmpty()) {
            throw new CustomNotFoundException("Not found user");
        }
        return users;
    }

    @Override
    public User retrieveUserById(long id) {
        Optional<User> userOptional = userDao.findUserById(id);
        userOptional.orElseThrow(() -> new CustomNotFoundException("Not found user by id " + id));
        return userOptional.get();
    }

    /**
     * @author : trungntm
     * @date : 3/11/2018 15h25
     */
    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public Page<User> retrieveUserByRolePaging(Long roleId, Optional<String> keyword, Optional<Integer> page, Optional<Integer> size) {
        return userDao.findUserByRolePaging(Optional.of(roleId).orElse((long) 0), keyword.orElse(""),
                new PageRequest(page.orElse(0), size.orElse(5), Sort.Direction.ASC, "id"));

    }
}
