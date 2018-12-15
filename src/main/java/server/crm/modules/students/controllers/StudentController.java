package server.crm.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import server.crm.core.converters.DtoToStudent;
import server.crm.core.model.StudentDto;
import server.crm.entities.Contact;
import server.crm.entities.Student;
import server.crm.modules.students.services.StudentService;

@RestController
@RequestMapping("api/v1.0/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private DtoToStudent converter;

    @GetMapping(params = {"page", "size", "sortBy"})
    public Page<Student> getAllStudent(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy) {
        return studentService.findAllStudentPaginated(page, size, Sort.by(sortBy));
    }

    @GetMapping(value = "/search", params = {"page", "size", "sortBy", "keyword"})
    public Page<Student> getAllStudentByKeyword(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("keyword") String keyword) {
        return studentService.findAllStudentByKeyword(keyword, page, size, Sort.by(sortBy));
    }

    @GetMapping(params = {"id"})
    public Student getDetailStudent(
            @RequestParam("id") Long id) {
        return studentService.findStudentById(id);
    }

    @PostMapping
    public Student createStudent(
            @RequestBody StudentDto student) {
        Student studentEntity = converter.convert(student);
        return studentService.createStudent(studentEntity);
    }

    @PutMapping
    public Student updateStudent(
            @RequestBody StudentDto student) {
        Student studentEntity = converter.convert(student);
        return studentService.updateStudent(studentEntity);
    }

    @DeleteMapping(params = {"id"})
    public Student deleteStudent(
            @RequestParam("id") Long id) {
        return studentService.deleteStudent(id);
    }
}
