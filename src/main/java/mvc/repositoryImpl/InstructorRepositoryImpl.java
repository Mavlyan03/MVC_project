package mvc.repositoryImpl;

import mvc.model.Company;
import mvc.model.Course;
import mvc.model.Instructor;

import mvc.repository.InstructorRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveInstructor(Long id,Instructor instructor) {
        Company company = entityManager.find(Company.class,id);
        company.addInstructor(instructor);
        instructor.setCompany(company);
        entityManager.persist(instructor);
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        Instructor instructor1 = entityManager.find(Instructor.class, id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        instructor1.setSpecialization(instructor.getSpecialization());
        entityManager.merge(instructor1);
    }

    @Override
    public List<Instructor> getAllInstructor(Long id) {
       return entityManager.createQuery("select i from Instructor i where i.company.companyId = :id",Instructor.class)
               .setParameter("id",id).getResultList();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public void deleteInstructorById(Long id) {
        Instructor instructor = entityManager.find(Instructor.class, id);
        for (Course course : instructor.getCourses()) {
            course.getInstructors();
        }
        entityManager.remove(instructor);
    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) {
        Instructor instructor = entityManager.find(Instructor.class, instructorId);
        Course course = entityManager.find(Course.class, courseId);
        instructor.addCourse(course);
        course.addInstructors(instructor);
        entityManager.merge(instructor);
    }
}
