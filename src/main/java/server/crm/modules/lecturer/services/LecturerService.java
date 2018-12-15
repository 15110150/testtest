package server.crm.modules.lecturer.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import server.crm.entities.Lecturer;

public interface LecturerService {
    Page<Lecturer> findAllLecturerPaginated(int page, int size, Sort sortBy);

    Lecturer createLecturer(Lecturer lecturer);

    Lecturer updateLecturer(Lecturer lecturer);

    Lecturer deleteLecturer(Long id);

    Page<Lecturer> findAllLecturerByKeyword(String keyword, int page, int size, Sort sortBy);

    Lecturer findLecturerById(Long id);
}
