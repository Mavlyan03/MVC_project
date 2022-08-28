package mvc.repository;

import mvc.model.Company;
import mvc.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface StudentRepository {

    void saveStudent(Long id,Student student);

    void updateStudent(Long id,Student student);

    List<Student> getAllStudents(Long id);

    Student getStudentById(Long id);

    void deleteStudentById(Long id);

    void assignStudentToCourse(Long studentId,Long courseId);


}
