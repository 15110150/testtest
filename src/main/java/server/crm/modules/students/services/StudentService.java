package server.crm.modules.students.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import server.crm.entities.Contact;
import server.crm.entities.Student;

public interface StudentService {
    Page<Student> findAllStudentPaginated(int page, int size, Sort sortBy);

    Student createStudent(Student student);

    Student updateStudent(Student student);

    Student deleteStudent(Long id);

    Page<Student> findAllStudentByKeyword(String keyword, int page, int size, Sort sortBy);

    Student findStudentById(Long id);
}
