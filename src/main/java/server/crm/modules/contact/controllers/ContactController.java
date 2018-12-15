package server.crm.modules.contact.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import server.crm.entities.Contact;
import server.crm.modules.contact.services.ContactService;

@RestController
@RequestMapping("api/v1.0/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping(params = {"page", "size", "sortBy"})
    public Page<Contact> getAllContact(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy) {
        return contactService.findAllContactPaginated(page, size, Sort.by(sortBy));
    }

//    @GetMapping(value = "/search", params = {"page", "size", "sortBy", "keyword"})
//    public Page<Contact> getAllStudentByKeyword(
//            @RequestParam("page") int page,
//            @RequestParam("size") int size,
//            @RequestParam("sortBy") String sortBy,
//            @RequestParam("keyword") String keyword) {
//        return studentService.findAllStudentByKeyword(keyword, page, size, Sort.by(sortBy));
//    }
//
//    @GetMapping(params = {"id"})
//    public Student getDetailStudent(
//            @RequestParam("id") Long id) {
//        return studentService.findStudentById(id);
//    }
//
    @PostMapping
    public Contact createContact(
            @RequestBody Contact contact) {
        return contactService.createContact(contact);
    }
//
//    @PutMapping
//    public Student updateStudent(
//            @RequestBody Student student) {
//        return studentService.updateStudent(student);
//    }
//
//    @DeleteMapping(params = {"id"})
//    public Student deleteStudent(
//            @RequestParam("id") Long id) {
//        return studentService.deleteStudent(id);
//    }
}
