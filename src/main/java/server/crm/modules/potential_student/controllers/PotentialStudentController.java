package server.crm.modules.potential_student.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import server.crm.entities.PotentialStudent;
import server.crm.modules.potential_student.services.PotentialStudentService;

@RestController
@RequestMapping("api/v1.0/potential_student")
public class PotentialStudentController {
    @Autowired
    private PotentialStudentService potentialStudentService;

    @GetMapping(params = {"page", "size", "sortBy"})
    public Page<PotentialStudent> getAllPotentialStudent(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy) {
        return potentialStudentService.findAllPotentialStudentPaginated(page, size, Sort.by(sortBy));
    }

    @GetMapping(value = "/search", params = {"page", "size", "sortBy", "keyword"})
    public Page<PotentialStudent> getAllStudentByKeyword(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("keyword") String keyword) {
        return potentialStudentService.findAllPotentialStudentByKeyword(keyword, page, size, Sort.by(sortBy));
    }

    @GetMapping(params = {"id"})
    public PotentialStudent getDetailPotentialStudent(
            @RequestParam("id") Long id) {
        return potentialStudentService.findPotentialStudentById(id);
    }

    @PostMapping
    public PotentialStudent createPotentialStudent(
            @RequestBody PotentialStudent potentialStudent) {
        return potentialStudentService.createPotentialStudent(potentialStudent);
    }

    @PutMapping
    public PotentialStudent updatePotentialStudent(
            @RequestBody PotentialStudent potentialStudent) {
        return potentialStudentService.updatePotentialStudent(potentialStudent);
    }

    @DeleteMapping(params = {"id"})
    public PotentialStudent deletePotentialStudent(
            @RequestParam("id") Long id) {
        return potentialStudentService.deletePotentialStudent(id);
    }
}
