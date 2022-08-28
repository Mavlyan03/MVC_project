package mvc.service;

import mvc.model.Course;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseService {

    void saveCourse(Long id, Course course);

    void updateCourse(Long id,Course course);

    List<Course> getAllCourse(Long id);

    Course getCourseById(Long id);

    void deleteCourseById(Long id);
}
