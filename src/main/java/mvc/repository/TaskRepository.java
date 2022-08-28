package mvc.repository;

import mvc.model.Task;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface TaskRepository {

    void saveTask(Long lessonId, Task task);

    void updateTask(Long id,Task task);

    Task getTaskById(Long id);

    List<Task> getAllTasks(Long id);

    void deleteTaskById(Long id);
}
