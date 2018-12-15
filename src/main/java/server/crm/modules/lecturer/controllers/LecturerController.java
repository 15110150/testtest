package server.crm.modules.lecturer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import server.crm.entities.Lecturer;
import server.crm.modules.lecturer.services.LecturerService;

@RestController
@RequestMapping("api/v1.0/lecturer")
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;

    @GetMapping(params = {"page", "size", "sortBy"})
    public Page<Lecturer> getAllLecturer(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy) {
        return lecturerService.findAllLecturerPaginated(page, size, Sort.by(sortBy));
    }

    @GetMapping(value = "/search", params = {"page", "size", "sortBy", "keyword"})
    public Page<Lecturer> getAllLecturerByKeyword(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("keyword") String keyword) {
        return lecturerService.findAllLecturerByKeyword(keyword, page, size, Sort.by(sortBy));
    }

    @GetMapping(params = {"id"})
    public Lecturer getDetailLecturer(
            @RequestParam("id") Long id) {
        return lecturerService.findLecturerById(id);
    }

    @PostMapping
    public Lecturer createLecturer(
            @RequestBody Lecturer lecturer) {
        return lecturerService.createLecturer(lecturer);
    }

    @PutMapping
    public Lecturer updateLecturer(
            @RequestBody Lecturer lecturer) {
        return lecturerService.updateLecturer(lecturer);
    }

    @DeleteMapping(params = {"id"})
    public Lecturer deleteLecturer(
            @RequestParam("id") Long id) {
        return lecturerService.deleteLecturer(id);
    }
}
