package server.crm.core.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import server.crm.core.model.StudentDto;
import server.crm.entities.*;
import server.crm.modules.students.repositories.StudentRepository;

import java.util.Optional;

@Component
public class DtoToStudent implements Converter<StudentDto, Student> {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public Student convert(StudentDto source) {
        if(source == null) return null;
        Student student = new Student();
        student.setId(source.getId());
        student.setName(source.getName());
        student.setContact(source.getContact());
        student.setBirthDay(source.getBirthDay());
        student.setJob(source.getJob());
        student.setStatus(source.isStatus());
        return student;
    }
}
