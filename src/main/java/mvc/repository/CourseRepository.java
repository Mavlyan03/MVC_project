package mvc.repository;

import mvc.model.Company;
import mvc.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CourseRepository {

    void saveCourse(Long id,Course course);

    void updateCourse(Long id,Course course);

    List<Course> getAllCourse(Long id);

    Course getCourseById(Long id);

    void deleteCourseById(Long id);
}
