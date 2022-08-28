package mvc.service;

import mvc.model.Lesson;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LessonService {

    void saveLesson(Long courseId, Lesson lesson);

    void updateLesson(Long id,Lesson lesson);

    List<Lesson> getAllLessons(Long id);

    Lesson getLessonById(Long id);

    void deleteLessonById(Long id);
}
