package server.crm.modules.courseclass.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import server.crm.core.api.AuthenticationFacade;
import server.crm.core.converters.DtoToClass;
import server.crm.core.model.ClassDto;
import server.crm.core.model.LoggedUser;
import server.crm.entities.Class;
import server.crm.entities.Student;
import server.crm.exceptions.ApiRuntimeException;
import server.crm.modules.courseclass.services.ClassService;
import server.crm.responses.base.BaseResponse;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/class")
public class ClassController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassController.class);

    @Autowired
    private ClassService classService;
    @Autowired
    private DtoToClass converter;
    @Autowired
    private AuthenticationFacade authenticationFacade;


    @GetMapping(value = {"", "/"}, params = {
            "page", "size", "sortBy"
    })
    public Page<Class> getAllClassPaginated(@RequestParam("page") int page,
                                            @RequestParam("size") int size,
                                            @RequestParam("sortBy") String sortBy) {

        return classService.getAllClassPaginated(page, size, sortBy);
    }

    @GetMapping(value = {"/search"}, params = {
            "page", "size", "sortBy", "keyWord", "courseId", "courseCat"
    })
    public Page<Class> searchClassPaginated(@RequestParam("page") int page,
                                            @RequestParam("size") int size,
                                            @RequestParam("sortBy") String sortBy,
                                            @RequestParam("keyWord") String keyWord,
                                            @RequestParam("courseId") String courseId) {
        return classService.searchClassByKeyWordCourseId(page, size, sortBy, keyWord, courseId);
    }


    @GetMapping(params = {"id"})
    public Class getClassById(@RequestParam("id") String id) {
        return classService.getClassById(id);
    }

    @PostMapping
    public Class createClass(@RequestBody ClassDto clazz) {
        LoggedUser.logIn(authenticationFacade.getAuthentication().getName());
        LOGGER.info("/course: id: {}, name: {}, status: {}", clazz.getId(), clazz.getName());
        Class classEntity = converter.convert(clazz);
        return classService.createClass(classEntity);
    }

    @PutMapping
    public Class updateClass(@RequestBody ClassDto clazz) {
        LoggedUser.logIn(authenticationFacade.getAuthentication().getName());
        LOGGER.info("/course: id: {}, name: {}, status: {}", clazz.getId(), clazz.getName(), clazz.isStatus());
        Class classEntity = converter.convert(clazz);
        return classService.updateClass(classEntity);
    }

    @PutMapping("/{id}/addStudent")
    public Class addStudentToClass(@PathVariable("id") String classId, @RequestBody String[] students) {
        LoggedUser.logIn(authenticationFacade.getAuthentication().getName());
        List<String> studetList = Arrays.asList(students);
        return classService.addStudentToClass(classId, studetList);
    }

    @DeleteMapping(params = {"id"})
    public BaseResponse deleteClass(@RequestParam("id") String id) {
        LoggedUser.logIn(authenticationFacade.getAuthentication().getName());
        try {
            if (classService.deleteClass(id)) {
                return new BaseResponse();
            } else return new BaseResponse();
        } catch (RuntimeException e) {
            throw new ApiRuntimeException("Unknown Exception. Need help!!!!!!!!!!!!!!!");
        }
    }

    @GetMapping("/{id}/students")
    public List<Student> getStudentInClass(@PathVariable("id") String id) {
        return classService.getStudentsInClass(id);
    }

    @GetMapping("/{id}/notStudents")
    public List<Student> getStudentNotInClass(@PathVariable("id") String id) {
        return classService.getStudentsNotInClass(id);
    }


}
