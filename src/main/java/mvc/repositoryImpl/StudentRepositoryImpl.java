package mvc.repositoryImpl;

import mvc.model.Company;
import mvc.model.Course;
import mvc.model.Instructor;
import mvc.model.Student;
import mvc.repository.StudentRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveStudent(Long companyId, Student student) {
        Company company = entityManager.find(Company.class,companyId);
        company.addStudent(student);
        student.setCompany(company);
        entityManager.persist(student);
    }

    @Override
    public void updateStudent(Long id,Student student) {
        Student student1 = entityManager.find(Student.class,id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setEmail(student.getEmail());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);
    }


    @Override
    public List<Student> getAllStudents(Long id) {
        return entityManager.createQuery("SELECT s FROM Student s where s.company.companyId = : id",Student.class)
                .setParameter("id",id).getResultList();
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public void deleteStudentById(Long id) {
        Student student = entityManager.find(Student.class,id);
        student.setCourse(null);
        entityManager.remove(student);
    }

    @Override
    public void assignStudentToCourse(Long studentId, Long courseId) {
        Student student = entityManager.find(Student.class, studentId);
        Course course = entityManager.find(Course.class, courseId);
        student.setCourse(course);
        course.addStudents(student);
        entityManager.merge(student);
    }


    @Override
    public List<Student> countOfStudents(Long id) {
        return entityManager.createQuery("SELECT COUNT(Student.studentId) FROM Student s where s.company.companyId = : id",Student.class)
                .setParameter("id",id).getResultList();
    }


}
