package server.crm.modules.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import server.crm.entities.Role;
import server.crm.entities.User;
import server.crm.exceptions.CustomNotFoundException;
import server.crm.modules.users.api.v1.dto.RoleDto;
import server.crm.modules.users.services.roles.RoleService;
import server.crm.modules.users.services.users.UserService;
import server.crm.responses.PageResponse;
import server.crm.responses.base.BaseResponse;
import server.crm.responses.base.Error;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1.0/roles")
public class RoleController {
    /**
     * @author : trungntm
     * @date : 25/10/2018 18h17
     * @content : inject roleService
     */
    @Autowired
    private RoleService roleService;

    /**
     * @author : trungntm
     * @date : 3/11/2018 17h58
     * @content : inject userService;
     */
    @Autowired
    private UserService userService;

    /**
     * @author : trungntm
     * @date : 25/10/2018 23h15
     * @content : get all role/search role by name. paging and sorting
     */
    @GetMapping("/list")
    public PageResponse retrieveRolePagingAndSorting(@RequestParam("name") Optional<String> name,
                                                     @RequestParam("page") Optional<Integer> page,
                                                     @RequestParam("size") Optional<Integer> size) {
        PageResponse pageResponse = new PageResponse();
        List<Error> errors = new ArrayList<>();
        Page<RoleDto> rolePage = null;

        try {
            rolePage = roleService.retrieveRoleOptionalNamePagingAndSorting(name, page, size);
        } catch (CustomNotFoundException ex) {
            Error error = new Error();
            error.setStatus(404);
            error.setMessage(ex.getMessage());
            errors.add(error);
        }

        pageResponse.setErrors(errors);
        pageResponse.setResult(rolePage.getContent());
        pageResponse.setPageNumber(rolePage.getTotalPages());
        System.out.println(rolePage.getTotalPages());
        return pageResponse;
    }

    /**
     * @author : trungntm
     * @date : 3/11/2018 17h20
     * @content : retrieve all users by role
     */
    @GetMapping("/users")
    public PageResponse retrieveUserByRole(@RequestParam("id") Long id,
                                           @RequestParam("keyword") Optional<String> keyword,
                                           @RequestParam("page") Optional<Integer> page,
                                           @RequestParam("size") Optional<Integer> size) {
        PageResponse pageResponse = new PageResponse();
        List<Error> errors = new ArrayList<>();
        Page<User> userPage = null;
        try {
            userPage = userService.retrieveUserByRolePaging(id, keyword, page, size);
        } catch (CustomNotFoundException ex) {
            Error error = new Error();
            error.setStatus(404);
            error.setMessage(ex.getMessage());
            errors.add(error);
        }
        pageResponse.setPageNumber(userPage.getTotalPages());
        pageResponse.setErrors(errors);
        pageResponse.setResult(userService.mapperArrays(userPage.getContent()));
        return pageResponse;
    }
//    @GetMapping("/{id}/users")
//    @SuppressWarnings("unchecked")
//    public PageResponse RetrieveUsersByRole(@PathVariable long id){
//        PageResponse pageResponse = new PageResponse();
//        List<Error> errors = new ArrayList<>();
//        List<User> users = new ArrayList<>();
//        try {
//            users.addAll(roleService.retrieveRoleById(id).getUsers());
//        } catch (CustomNotFoundException ex) {
//            Error error = new Error();
//            error.setStatus(404);
//            error.setMessage(ex.getMessage());
//            errors.add(error);
//        }
//        pageResponse.setErrors(errors);
//        pageResponse.setResult(userService.mapperArrays(users));
//        return pageResponse;
//    }

    /**
     * @author : trungntm
     * @date : 3/11/2018 18h33
     * @content : insert role
     */
    @PostMapping("")
    public BaseResponse InsertRole(@RequestBody Role newRole, Authentication authentication) {
        BaseResponse baseResponse = new BaseResponse();
        List<Error> errors = new ArrayList<>();
        Role role = new Role();
        try {
            role = roleService.insertRole(newRole, authentication.getName());
        } catch (Exception ex) {
            Error error = new Error();
            error.setMessage(ex.getMessage());
            error.setStatus(407);
            errors.add(error);
        }
        baseResponse.setErrors(errors);
        baseResponse.setResult(roleService.mapperSingle(role));
        return baseResponse;
    }

    /**
     * @author : trungntm
     * @date : 3/11/2018 23h25
     * @content : update role
     */
    @PutMapping("")
    public BaseResponse UpdateRole(@RequestBody Role updateRole, Authentication authentication) {
        BaseResponse baseResponse = new BaseResponse();
        List<Error> errors = new ArrayList<>();
        Role role = new Role();
        try {
            role = roleService.updateRole(updateRole, authentication.getName());
        } catch (CustomNotFoundException ex) {
            Error error = new Error();
            error.setMessage(ex.getMessage());
            error.setStatus(404);
            errors.add(error);
        }
        baseResponse.setErrors(errors);
        baseResponse.setResult(roleService.mapperSingle(role));
        return baseResponse;
    }

    /**
     * @author : trungntm
     * @date : 4/11/2018 1h03
     * @content : delete role
     */
    @PutMapping("/{id}")
    public BaseResponse deleteRole(@PathVariable long id, Authentication authentication) {
        BaseResponse baseResponse = new BaseResponse();
        List<Error> errors = new ArrayList<>();
        Role role = new Role();
        try {
            role = roleService.deleteRole(id, authentication.getName());
        } catch (CustomNotFoundException ex) {
            Error error = new Error();
            error.setMessage(ex.getMessage());
            error.setStatus(404);
            errors.add(error);
        }
        baseResponse.setErrors(errors);
        baseResponse.setResult(roleService.mapperSingle(role));
        return baseResponse;
    }

    /**
     * @author : trungntm
     * @dtae : 4/11/2018 1h24
     * @content : retrieve role by id
     */
    @GetMapping("/{id}")
    public BaseResponse retrieveRoleById(@PathVariable long id) {
        BaseResponse baseResponse = new BaseResponse();
        List<Error> errors = new ArrayList<>();
        Role role = new Role();
        try {
            role = roleService.retrieveRoleById(id);
        } catch (CustomNotFoundException ex) {
            Error error = new Error();
            error.setStatus(404);
            error.setMessage(ex.getMessage());
            errors.add(error);
        }
        baseResponse.setErrors(errors);
        baseResponse.setResult(roleService.mapperSingle(role));
        return baseResponse;
    }
}
