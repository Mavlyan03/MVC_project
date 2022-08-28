package mvc.repositoryImpl;

import mvc.model.Lesson;
import mvc.model.Task;
import mvc.repository.TaskRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveTask(Long lessonId, Task task) {
        Lesson lesson = entityManager.find(Lesson.class,lessonId);
        lesson.addTasks(task);
        task.setLessons(lesson);
        entityManager.persist(task);
    }

    @Override
    public void updateTask(Long id,Task task) {
        Task task1 = entityManager.find(Task.class,id);
        task1.setTaskName(task.getTaskName());
        task1.setTaskText(task.getTaskText());
        task1.setDeadline(task.getDeadline());
        entityManager.merge(task1);
    }

    @Override
    public Task getTaskById(Long id) {
        return entityManager.find(Task.class,id);
    }


    @Override
    public List<Task> getAllTasks(Long id) {
        return entityManager.createQuery("SELECT t FROM Task t where t.lessons.lessonId = : id",Task.class)
                .setParameter("id",id).getResultList();
    }

    @Override
    public void deleteTaskById(Long id) {
        Task task = entityManager.find(Task.class,id);
        task.setLessons(null);
        entityManager.remove(task);
    }
}
