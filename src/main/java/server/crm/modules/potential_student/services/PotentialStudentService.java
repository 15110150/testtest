package server.crm.modules.potential_student.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import server.crm.entities.PotentialStudent;

public interface PotentialStudentService {
    Page<PotentialStudent> findAllPotentialStudentPaginated(int page, int size, Sort sortBy);

    PotentialStudent createPotentialStudent(PotentialStudent potentialStudent);

    PotentialStudent updatePotentialStudent(PotentialStudent potentialStudent);

    PotentialStudent deletePotentialStudent(Long id);

    Page<PotentialStudent> findAllPotentialStudentByKeyword(String keyword, int page, int size, Sort sortBy);

    PotentialStudent findPotentialStudentById(Long id);
}
