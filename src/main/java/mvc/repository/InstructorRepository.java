package mvc.repository;

import mvc.model.Company;
import mvc.model.Instructor;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface InstructorRepository {

    void saveInstructor(Long id,Instructor instructor);

    void updateInstructor(Long id,Instructor instructor);

    List<Instructor> getAllInstructor(Long id);

    Instructor getInstructorById(Long id);

    void deleteInstructorById(Long id);

    void assignInstructorToCourse(Long instructorId,Long courseId);
}
