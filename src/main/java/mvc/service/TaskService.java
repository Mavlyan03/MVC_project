package mvc.service;

import mvc.model.Task;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TaskService {

    void saveTask(Long lessonId, Task task);

    void updateTask(Long id,Task task);

    Task getTaskById(Long id);

    List<Task> getAllTasks(Long id);

    void deleteTaskById(Long id);
}
