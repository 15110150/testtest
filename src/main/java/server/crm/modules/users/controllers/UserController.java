package server.crm.modules.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import server.crm.entities.Role;
import server.crm.entities.User;
import server.crm.exceptions.CustomNotFoundException;
import server.crm.modules.users.services.roles.RoleService;
import server.crm.modules.users.services.users.UserService;
import server.crm.responses.base.BaseResponse;
import server.crm.responses.base.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/api/v1.0/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * @author : trungntm
     * @date : 22/10/2018 2:20AM
     * @content : add method test in UserController
     **/
    @GetMapping("/test")
    public String test() {
        return "permit users";
    }

    /**
     * @author : trungntm
     * @date : 23/10/2018 23h42
     * @content : add method GET to get user by username, return type BaseResponse
     * */
//    @GetMapping("/{username}")
//    public BaseResponse retrieveUserByUsername(@PathVariable String username){
//        UserDto userDto=new UserDto();
//        BaseResponse baseResponse=new BaseResponse();
//        ArrayList<Error> errors=new ArrayList<>();
//        try{
//            userDto=userService.retrieveUserByUsername(username);
//        }
//        catch (CustomNotFoundException ex){
//            Error error=new Error();
//            error.setStatus(HttpStatus.NOT_FOUND.value());
//            error.setMessage(ex.getMessage());
//            errors.add(error);
//        }
//        baseResponse.setErrors(errors);
//        baseResponse.setResult(userDto);
//        return baseResponse;
//    }

    /**
     * @author: trungntm
     * @date: 25/10/2018 12h55
     * @contet : GET controllers to Get all users
     */
    @GetMapping("/list")
    public BaseResponse retrieveAllUsers() {
        BaseResponse baseResponse = new BaseResponse();
        List<Error> errors = new ArrayList<>();
        List<User> users = new ArrayList<>();
        try {
            users = userService.retrieveAllUser();
        } catch (CustomNotFoundException ex) {
            Error error = new Error();
            error.setMessage(ex.getMessage());
            error.setStatus(404);
            errors.add(error);
        } catch (Exception ex) {
            Error error = new Error();
            error.setMessage(ex.getMessage());
            error.setStatus(407);
            errors.add(error);
        }
        baseResponse.setErrors(errors);
        baseResponse.setResult(userService.mapperArrays(users));
        return baseResponse;
    }

    /**
     * @author : trungntm
     * @date : 25/10/2018 13h22
     * @content : GET controllers get user by id,
     */
    @GetMapping("/{id}")
    public BaseResponse retrieveUserById(@PathVariable long id) {
        BaseResponse baseResponse = new BaseResponse();
        List<Error> errors = new ArrayList<>();
        User user = new User();
        try {
            user = userService.retrieveUserById(id);
        } catch (CustomNotFoundException ex) {
            Error error = new Error();
            error.setStatus(404);
            error.setMessage(ex.getMessage());
            errors.add(error);
        }
        baseResponse.setErrors(errors);
        baseResponse.setResult(userService.mapperSingle(user));
        return baseResponse;
    }

    /**
     * @author : trungntm
     * @date : 3/11/2018 15h09
     * @content : update role for user
     */
    @PutMapping("/{uid}/{rid}")
    @SuppressWarnings("unchecked")
    public BaseResponse updateRoleForUser(@PathVariable long uid, @PathVariable long rid) {
        BaseResponse baseResponse = new BaseResponse();
        List<Error> errors = new ArrayList<>();
        User user = new User();

        try {
            user = userService.retrieveUserById(uid);
            Role role = roleService.retrieveRoleById(rid);
            Set<Role> rolesExist = user.getRoles();

            if (rolesExist.contains(role)) {
                rolesExist.remove(role);
            } else {
                rolesExist.add(role);
            }

            user.setRoles(rolesExist);
            userService.save(user);
        } catch (CustomNotFoundException ex) {
            Error error = new Error();
            error.setMessage(ex.getMessage());
            error.setStatus(404);
            errors.add(error);
        }

        baseResponse.setErrors(errors);
        baseResponse.setResult(userService.mapperSingle(user));
        return baseResponse;
    }

    /**
     * @author : trungntm
     * @date : 4/11/2018 1h36
     * @content : retrieve current user
     */
    @GetMapping("/current")
    public BaseResponse retrieveCurrentUser(Authentication authentication) {
        BaseResponse baseResponse = new BaseResponse();
        List<Error> errors = new ArrayList<>();
        User user = new User();

        try {
            user = userService.retrieveUserByUsername(authentication.getName());
        } catch (CustomNotFoundException ex) {
            Error error = new Error();

            error.setStatus(404);
            error.setMessage(ex.getMessage());
            errors.add(error);
        }

        baseResponse.setErrors(errors);
        baseResponse.setResult(userService.mapperSingle(user));
        return baseResponse;
    }
}
