package server.crm.modules.courseclass.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import server.crm.core.exception.NotFoundException;
import server.crm.core.model.LoggedUser;
import server.crm.entities.Class;
import server.crm.entities.Mark;
import server.crm.entities.Student;
import server.crm.exceptions.ApiRuntimeException;
import server.crm.modules.courseclass.repositories.ClassRepository;
import server.crm.modules.courseclass.repositories.MarkRepository;
import server.crm.modules.courseclass.services.ClassService;
import server.crm.modules.students.repositories.StudentRepository;
//import server.crm.modules.students.dao.StudentDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MarkRepository markRepository;

    @Override
    public Page<Class> getAllClassPaginated(int page, int size, String sortBy) {
        return classRepository.findAllByStatus(PageRequest.of(page, size, Sort.by(sortBy)), true);
    }

    @Override
    public Page<Class> searchClassByKeyWordCourseId(int page, int size, String sortBy, String keyWord, String courseId) {
        try {
            Long id = Long.valueOf(courseId);
            return classRepository.findClassesByNameOrDescriptionAndCourseId(PageRequest.of(page, size, Sort.by(sortBy)),
                    keyWord, keyWord, id, true);
        } catch (Exception e) {
            throw new ApiRuntimeException("Maybe in parsing id");
        }
    }

    @Override
    public Class getClassById(String id) {
        try {
            return classRepository.findByIdAndStatus(Long.valueOf(id), true);
        } catch (NumberFormatException e) {
            throw new ApiRuntimeException("Wrong ID number!");
        } catch (Exception e) {
            throw new ApiRuntimeException("Something went wrong!");
        }
    }

    @Override
    public Class createClass(Class clazz) {
        try {
            if (classRepository.findById(clazz.getId()).isPresent()) {
                throw new ApiRuntimeException("Class is already exist");
            }
            clazz.setStatus(true);
            clazz.setCreatedBy(LoggedUser.get());
            clazz.setCreatedDate(new Date());
            return classRepository.save(clazz);
        } catch (ApiRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiRuntimeException("Unsuccessful");
        }
    }

    @Override
    public Class updateClass(Class clazz) {
        try {
            Optional<Class> targetClass = classRepository.findById(clazz.getId());
            if (targetClass.isPresent()) {
                clazz.setCreatedDate(targetClass.get().getCreatedDate());
                clazz.setCreatedBy(targetClass.get().getCreatedBy());
                clazz.setUpdatedBy(LoggedUser.get());
                clazz.setUpdatedDate(new Date());
                return classRepository.save(clazz);
            }
            throw new ApiRuntimeException("Can't find class");
        } catch (ApiRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiRuntimeException("Can't save class");
        }
    }

    @Override
    public boolean deleteClass(String id) {
        try {
            Optional<Class> target = classRepository.findById(Long.valueOf(id));
            if (target.isPresent()) {
                target.get().setStatus(false);
                target.get().setUpdatedBy(LoggedUser.get());
                target.get().setUpdatedDate(new Date());
                classRepository.delete(target.get());
                return true;
            } else {
                throw new ApiRuntimeException("Can't find class");
            }
        } catch (ApiRuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new ApiRuntimeException("Can't save class");
        }
    }

    @Override
    public Class addStudentToClass(String classId, List<String> studentList) {
        try {
            Optional<Class> classTarget = classRepository.findById(Long.valueOf(classId));
            if (classTarget.isPresent()) {
                List<Student> students = this.getListStudent(studentList);
                // TODO: implement here, add Mark repository
                for (Student student : students
                ) {
                    Mark mark = new Mark();
                    mark.setStudent(student);
                    mark.setAclass(classTarget.get());
                    mark.setCreatedBy(LoggedUser.get());
                    markRepository.save(mark);
                }
                return classRepository.findById(Long.valueOf(classId)).get();

            }
            throw new ApiRuntimeException("Can't find class");
        } catch (ApiRuntimeException e) {
            throw e;
        }
    }

    private List<Student> getListStudent(List<String> studentList) {
        List<Student> students = new ArrayList<>();
        try {
            studentList.forEach(s -> {
                try {
                    Long studentId = Long.valueOf(s);
                    students.add(studentRepository.findById(studentId).get());
                } catch (Exception e) {
                    System.out.println("Something was wrong in operating with value s = " + s);
                }

            });
        } catch (Exception e) {

        }
        return students;
    }

    @Override
    public List<Student> getStudentsInClass(String classId) {
        try {
            Long id = Long.valueOf(classId);
            Optional<Class> targetClass = classRepository.findById(id);
            if (targetClass.isPresent()) {
                List<Student> listStudent = new ArrayList<>();
                targetClass.get().getMarks().forEach(mark -> listStudent.add(mark.getStudent()));
                return listStudent;
            } else {
                throw new NotFoundException("Wrong Class ID");
            }
        } catch (Exception e) {
            throw new ApiRuntimeException("Parse ID Error");
        }
    }

    @Override
    public List<Student> getStudentsNotInClass(String classId) {
        try {
            Long id = Long.valueOf(classId);
            Optional<Class> targetClass = classRepository.findById(id);
            if (targetClass.isPresent()) {
                List<Long> listStudent = new ArrayList<>();
                targetClass.get().getMarks().forEach(mark -> listStudent.add(mark.getStudent().getId()));
                return studentRepository.findAllStudentExceptStudentInAClass(listStudent);
            } else {
                throw new NotFoundException("Wrong Class ID");
            }
        } catch (Exception e) {
            throw new ApiRuntimeException("Parse ID Error");
        }
    }
}
