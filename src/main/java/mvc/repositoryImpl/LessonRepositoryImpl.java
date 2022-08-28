package mvc.repositoryImpl;

import mvc.model.Course;
import mvc.model.Lesson;
import mvc.repository.LessonRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LessonRepositoryImpl implements LessonRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveLesson(Long courseId, Lesson lesson) {
        Course course = entityManager.find(Course.class,courseId);
        course.addLessons(lesson);
        lesson.setCourses(course);
        entityManager.persist(course);
    }

    @Override
    public void updateLesson(Long id,Lesson lesson) {
        Lesson lesson1 = entityManager.find(Lesson.class,id);
        lesson1.setLessonName(lesson.getLessonName());
        lesson1.setVideo(lesson.getVideo());
        entityManager.merge(lesson1);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return entityManager.find(Lesson.class,id);
    }

    @Override
    public void deleteLessonById(Long id) {
        Lesson lesson = entityManager.find(Lesson.class,id);
        lesson.setCourses(null);
        entityManager.remove(lesson);
    }

    @Override
    public List<Lesson> getAllLessons(Long id) {
        return entityManager.createQuery("SELECT l FROM Lesson l where l.courses.courseId = : id",Lesson.class)
                .setParameter("id",id).getResultList();
    }

}
