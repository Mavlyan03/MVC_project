package mvc.repository;

import mvc.model.Company;
import mvc.model.Lesson;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface LessonRepository {

    void saveLesson(Long courseId,Lesson lesson);

    void updateLesson(Long id,Lesson lesson);

    List<Lesson> getAllLessons(Long id);

    Lesson getLessonById(Long id);

    void deleteLessonById(Long id);
}
