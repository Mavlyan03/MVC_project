package mvc.api;

import mvc.model.Lesson;
import mvc.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("lessons")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/allLessons/{courseId}")
    private String getAllLessons(@PathVariable("courseId")Long courseId, Model model) {
        model.addAttribute("allLessons",lessonService.getAllLessons(courseId));
        model.addAttribute("courseId",courseId);
        return "lesson/mainLesson";
    }

    @GetMapping("{courseId}/newLesson")
    private String newLesson(@PathVariable("courseId")Long id,Model model) {
        model.addAttribute("newLesson",new Lesson());
        model.addAttribute("courseId",id);
        return "lesson/newLesson";
    }

    @PostMapping("{courseId}/saveLesson")
    private String saveLesson(@PathVariable("courseId")Long id, @ModelAttribute("newLesson")Lesson lesson) {
        lessonService.saveLesson(id,lesson);
        return "redirect:/lessons/allLessons/ " + id;
    }

    @GetMapping("/getLesson/{lessonId}")
    private String getLessonById(@PathVariable("lessonId")Long id,Model model) {
        model.addAttribute("lesson",lessonService.getLessonById(id));
        return "lesson/mainLesson";
    }

    @GetMapping("/updateLesson/{lessonId}")
    private String updateLesson(@PathVariable("lessonId")Long lessonId,Model model) {
        Lesson lesson = lessonService.getLessonById(lessonId);
        model.addAttribute("lesson",lesson);
        model.addAttribute("courseId",lesson.getCourses().getCourseId());
        return "lesson/updateLesson";
    }

    @PostMapping("/{courseId}/{lessonId}/saveUpdateLesson")
    private String saveUpdateLesson(@PathVariable("courseId")Long courseId,
                                    @PathVariable("lessonId")Long lessonId,
                                    @ModelAttribute("lesson")Lesson lesson) {
        lessonService.updateLesson(lessonId,lesson);
        return "redirect:/lessons/allLessons/ " + courseId;
    }

    @PostMapping("/{courseId}/{lessonId}/deleteLesson")
    private String deleteLesson(@PathVariable("courseId")Long id,@PathVariable("lessonId")Long lessonId) {
        lessonService.deleteLessonById(lessonId);
        return "redirect:/lessons/allLessons/ " + id;
    }

}
