package mvc.serviceImpl;

import mvc.model.Student;
import mvc.repository.StudentRepository;
import mvc.repositoryImpl.StudentRepositoryImpl;
import mvc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void saveStudent(Long id, Student student) {
        studentRepository.saveStudent(id,student);
    }

    @Override
    public void updateStudent(Long id,Student student) {
        studentRepository.updateStudent(id,student);
    }

    @Override
    public List<Student> getAllStudents(Long id) {
        return studentRepository.getAllStudents(id);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.getStudentById(id);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteStudentById(id);
    }

    @Override
    public void assignStudentToCourse(Long studentId, Long courseId) {
        studentRepository.assignStudentToCourse(studentId,courseId);
    }

    @Override
    public List<Student> countOfStudents(Long id) {
        return studentRepository.countOfStudents(id);
    }

}
