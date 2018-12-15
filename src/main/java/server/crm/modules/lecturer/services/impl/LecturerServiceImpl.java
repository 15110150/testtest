package server.crm.modules.lecturer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.crm.core.model.LoggedUser;
import server.crm.entities.Lecturer;
import server.crm.exceptions.ApiRuntimeException;
import server.crm.modules.lecturer.repositories.LecturerRepository;
import server.crm.modules.lecturer.services.LecturerService;

import java.util.Date;
import java.util.Optional;

@Service
public class LecturerServiceImpl implements LecturerService {
    @Autowired
    private LecturerRepository lecturerRepository;

    @Override
    public Page<Lecturer> findAllLecturerPaginated(int page, int size, Sort sortBy) {
        return lecturerRepository.findAllByStatusIsTrue(PageRequest.of(page, size, sortBy));
    }

    @Override
    public Lecturer createLecturer(Lecturer lecturer) {
        try {
            if (lecturerRepository.findById(lecturer.getId()).isPresent()) {
                throw new ApiRuntimeException("Lecturer is already exist");
            }
            lecturer.setStatus(true);
            lecturer.setCreatedBy(LoggedUser.get());
            lecturer.setCreatedDate(new Date());
            return lecturerRepository.save(lecturer);
        } catch (ApiRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiRuntimeException("Unsuccessful");
        }
    }

    @Override
    public Lecturer updateLecturer(Lecturer lecturer) {
        try {
            Optional<Lecturer> targetLEcturer = lecturerRepository.findById(lecturer.getId());
            if (targetLEcturer.isPresent()) {
                lecturer.setCreatedDate(targetLEcturer.get().getCreatedDate());
                lecturer.setCreatedBy(targetLEcturer.get().getCreatedBy());
                lecturer.setUpdatedBy(LoggedUser.get());
                lecturer.setUpdatedDate(new Date());
                return lecturerRepository.save(lecturer);
            }
            throw new ApiRuntimeException("Can't find class");
        } catch (ApiRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiRuntimeException("Can't save class");
        }
    }

    @Override
    public Lecturer deleteLecturer(Long id) {
        Lecturer lecturer = lecturerRepository.findById(id).get();
        lecturer.setStatus(false);
        return lecturerRepository.save(lecturer);
    }

    @Override
    public Page<Lecturer> findAllLecturerByKeyword(String keyword, int page, int size, Sort sortBy) {
        return lecturerRepository.findByNameContainingAndStatusIsTrueIgnoreCase(PageRequest.of(page, size, sortBy), keyword);
    }

    @Override
    public Lecturer findLecturerById(Long id) {
        return lecturerRepository.findById(id).get();
    }
}
