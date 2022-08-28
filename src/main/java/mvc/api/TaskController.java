package mvc.api;

import mvc.model.Task;
import mvc.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/allTasks/{lessonId}")
    private String getAllTasks(@PathVariable("lessonId")Long lessonId, Model model) {
        model.addAttribute("allTasks", taskService.getAllTasks(lessonId));
        model.addAttribute("lessonId",lessonId);
        return "task/mainTask";
    }

    @GetMapping("{lessonId}/newTask")
    private String newTask(@PathVariable("lessonId")Long id,Model model) {
        model.addAttribute("newTask",new Task());
        model.addAttribute("lessonId",id);
        return "task/newTask";
    }

    @PostMapping("{lessonId}/saveTask")
    private String saveTask(@PathVariable("lessonId")Long id, @ModelAttribute("newTask")Task task) {
        taskService.saveTask(id,task);
        return "redirect:/tasks/allTasks/ "+ id;
    }

    @GetMapping("/getTask/{taskId}")
    private String getLessonById(@PathVariable("taskId")Long id,Model model) {
        model.addAttribute("task",taskService.getTaskById(id));
        return "task/mainTask";
    }

    @GetMapping("/updateTask/{taskId}")
    private String updateTask(@PathVariable("taskId")Long taskId,Model model) {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task",task);
        model.addAttribute("lessonId",task.getLessons().getLessonId());
        return "task/updateTask";
    }

    @PostMapping("/{lessonId}/{taskId}/saveUpdateTask")
    private String saveUpdateTask(@PathVariable("lessonId")Long lessonId,
                                  @PathVariable("taskId")Long taskId,
                                  @ModelAttribute("task")Task task) {
        taskService.updateTask(taskId,task);
        return "redirect:/tasks/allTasks/ " + lessonId;

    }

//    @PostMapping("/{courseId}/{lessonId}/deleteLesson")
//    private String deleteLesson(@PathVariable("courseId")Long id,@PathVariable("lessonId")Long lessonId) {
//        lessonService.deleteLessonById(lessonId);
//        return "redirect:/lessons/allLessons/" + id;
//    }
    @PostMapping("/{lessonId}/{taskId}/deleteTask")
    private String deleteTask(@PathVariable("lessonId")Long id,@PathVariable("taskId")Long taskId) {
        taskService.deleteTaskById(taskId);
        return "redirect:/tasks/allTasks/ " + id;
    }
}
