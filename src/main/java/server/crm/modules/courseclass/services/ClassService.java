package server.crm.modules.courseclass.services;

import org.springframework.data.domain.Page;
import server.crm.entities.Class;
import server.crm.entities.Student;

import java.util.List;

public interface ClassService {
    Page<Class> getAllClassPaginated(int page, int size, String sortBy);

    Page<Class> searchClassByKeyWordCourseId(int page, int size, String sortBy, String keyWord, String courseId);

    Class getClassById(String id);

    Class createClass(Class clazz);

    Class updateClass(Class clazz);

    boolean deleteClass(String id);

    Class addStudentToClass(String classId, List<String> studentList);

    List<Student> getStudentsInClass(String classId);

    List<Student> getStudentsNotInClass(String classId);

//    Map<String, Object> getClassStatisticNumbers();

}