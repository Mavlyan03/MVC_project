package mvc.repositoryImpl;

import mvc.model.Company;
import mvc.model.Course;

import mvc.repository.CourseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveCourse(Long companyId, Course course) {
        Company company = entityManager.find(Company.class,companyId);
        company.addCourse(course);
        course.setCompany(company);
        entityManager.persist(course);
    }

    @Override
    public void updateCourse(Long id,Course course) {
        Course course1 = entityManager.find(Course.class,id);
        course1.setCourse_name(course.getCourse_name());
        course1.setDateOfStart(course.getDateOfStart());
        course1.setDuration(course.getDuration());
        course1.setImage(course.getImage());
        course1.setDescription(course.getDescription());
        entityManager.merge(course1);
    }

    @Override
    public List<Course> getAllCourse(Long id) {
        return entityManager.createQuery("SELECT c FROM Course c where c.company.companyId = : id",Course.class)
                .setParameter("id",id).getResultList();
    }

    @Override
    public Course getCourseById(Long id) {
        return entityManager.find(Course.class,id);
    }

    @Override
    public void deleteCourseById(Long id) {
        Course course = entityManager.find(Course.class,id);
        course.setCompany(null);
        entityManager.remove(course);

    }


}
