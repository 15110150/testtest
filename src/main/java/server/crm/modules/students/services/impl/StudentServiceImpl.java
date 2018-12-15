package server.crm.modules.students.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.crm.core.model.LoggedUser;
import server.crm.entities.Contact;
import server.crm.entities.Mark;
import server.crm.entities.Student;
import server.crm.exceptions.ApiRuntimeException;
import server.crm.modules.contact.repositories.ContactRepository;
import server.crm.modules.courseclass.repositories.MarkRepository;
import server.crm.modules.students.repositories.StudentRepository;
import server.crm.modules.students.services.StudentService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private MarkRepository markRepository;

    @Override
    public Page<Student> findAllStudentPaginated(int page, int size, Sort sortBy) {
        return studentRepository.findAllByStatusIsTrue(PageRequest.of(page, size, sortBy));
    }

    @Override
    public Student createStudent(Student student) {
        try {
            if (studentRepository.findById(student.getId()).isPresent()) {
                throw new ApiRuntimeException("Student is already exist");
            }
            student.setStatus(true);
            student.setCreatedBy(LoggedUser.get());
            student.setCreatedDate(new Date());
            Student student1 = studentRepository.save(student);

            Contact contact = student1.getContact();
            contact.setStatus(true);
            contact.setCreatedBy(LoggedUser.get());
            contact.setCreatedDate(new Date());
            contact.setStudents(student1);

            contactRepository.save(contact);
            return student1;

        } catch (ApiRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiRuntimeException("Unsuccessful");
        }
    }

    @Override
    public Student updateStudent(Student student) {
        try {
            Optional<Student> targetStudent = studentRepository.findById(student.getId());
            if (targetStudent.isPresent()) {
                student.setCreatedDate(targetStudent.get().getCreatedDate());
                student.setCreatedBy(targetStudent.get().getCreatedBy());
                student.setUpdatedBy(LoggedUser.get());
                student.setUpdatedDate(new Date());

                Contact contact = student.getContact();
                contact.setCreatedDate(targetStudent.get().getCreatedDate());
                contact.setCreatedBy(targetStudent.get().getCreatedBy());
                contact.setUpdatedBy(LoggedUser.get());
                contact.setUpdatedDate(new Date());
                contactRepository.save(contact);

                return studentRepository.save(student);
            }
            throw new ApiRuntimeException("Can't find student");
        } catch (ApiRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiRuntimeException("Can't save class");
        }
    }

    @Override
    public Student deleteStudent (Long id) {
        Student student = studentRepository.findById(id).get();
        Contact contact = student.getContact();
        student.setStatus(false);
        contact.setStatus(false);
        contactRepository.save(contact);
        return studentRepository.save(student);
    }

    @Override
    public Page<Student> findAllStudentByKeyword(String keyword, int page, int size, Sort sortBy) {
        return studentRepository.findByNameContainingAndStatusIsTrueIgnoreCase(PageRequest.of(page, size, sortBy), keyword);
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id).get();
    }
}
