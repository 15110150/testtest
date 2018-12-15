package server.crm.modules.potential_student.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.crm.core.model.LoggedUser;
import server.crm.entities.Contact;
import server.crm.entities.PotentialStudent;
import server.crm.entities.Student;
import server.crm.exceptions.ApiRuntimeException;
import server.crm.modules.contact.repositories.ContactRepository;
import server.crm.modules.potential_student.repositories.PotentialStudentRepository;
import server.crm.modules.potential_student.services.PotentialStudentService;

import java.util.Date;
import java.util.Optional;

@Service
public class PotentialStudentServiceImpl implements PotentialStudentService {
    @Autowired
    private PotentialStudentRepository potentialStudentRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Page<PotentialStudent> findAllPotentialStudentPaginated(int page, int size, Sort sortBy) {
        return potentialStudentRepository.findAllByStatusIsTrue(PageRequest.of(page, size, sortBy));
    }

    @Override
    public PotentialStudent createPotentialStudent(PotentialStudent potentialStudent) {
        try {
            if (potentialStudentRepository.findById(potentialStudent.getId()).isPresent()) {
                throw new ApiRuntimeException("Potential Student is already exist");
            }
            potentialStudent.setStatus(true);
            potentialStudent.setCreatedBy(LoggedUser.get());
            potentialStudent.setCreatedDate(new Date());
            PotentialStudent student1 = potentialStudentRepository.save(potentialStudent);

            Contact contact = student1.getContact();
            contact.setStatus(true);
            contact.setCreatedBy(LoggedUser.get());
            contact.setCreatedDate(new Date());
            contact.setPotentialStudents(student1);

            contactRepository.save(contact);
            return student1;

        } catch (ApiRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiRuntimeException("Unsuccessful");
        }
    }

    @Override
    public PotentialStudent updatePotentialStudent(PotentialStudent potentialStudent) {
        try {
            Optional<PotentialStudent> targetStudent = potentialStudentRepository.findById(potentialStudent.getId());
            if (targetStudent.isPresent()) {
                potentialStudent.setCreatedDate(targetStudent.get().getCreatedDate());
                potentialStudent.setCreatedBy(targetStudent.get().getCreatedBy());
                potentialStudent.setUpdatedBy(LoggedUser.get());
                potentialStudent.setUpdatedDate(new Date());

                Contact contact = potentialStudent.getContact();
                contact.setCreatedDate(targetStudent.get().getCreatedDate());
                contact.setCreatedBy(targetStudent.get().getCreatedBy());
                contact.setUpdatedBy(LoggedUser.get());
                contact.setUpdatedDate(new Date());
                contactRepository.save(contact);

                return potentialStudentRepository.save(potentialStudent);
            }
            throw new ApiRuntimeException("Can't find Potential Student");
        } catch (ApiRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiRuntimeException("Can't save class");
        }
    }

    @Override
    public PotentialStudent deletePotentialStudent (Long id) {
        PotentialStudent potentialStudent = potentialStudentRepository.findById(id).get();
        Contact contact = potentialStudent.getContact();
        potentialStudent.setStatus(false);
        contact.setStatus(false);
        contactRepository.save(contact);
        return potentialStudentRepository.save(potentialStudent);
    }

    @Override
    public Page<PotentialStudent> findAllPotentialStudentByKeyword(String keyword, int page, int size, Sort sortBy) {
        return potentialStudentRepository.findByNameContainingAndStatusIsTrueIgnoreCase(PageRequest.of(page, size, sortBy), keyword);
    }

    @Override
    public PotentialStudent findPotentialStudentById(Long id) {
        return potentialStudentRepository.findById(id).get();
    }
}
