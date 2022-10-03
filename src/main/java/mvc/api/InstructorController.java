package mvc.api;

import mvc.model.Instructor;
import mvc.service.CourseService;
import mvc.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    private final CourseService courseService;

    @Autowired
    public InstructorController(InstructorService instructorService, CourseService courseService) {
        this.instructorService = instructorService;
        this.courseService = courseService;
    }

    @GetMapping("/allInstructors/{companyId}")
    public String getAllInstructors(@PathVariable("companyId")Long id,Model model) {
        model.addAttribute("allInstructors",instructorService.getAllInstructor(id));
        model.addAttribute("companyId",id);
        model.addAttribute("instructors",courseService.getAllCourse(id));
        return "instructor/mainInstructor";
    }


    @GetMapping("{companyId}/newInstructor")
    public String newInstructor(@PathVariable("companyId")Long id, Model model) {
        model.addAttribute("newInstructor",new Instructor());
        model.addAttribute("companyId",id);
        return "instructor/newInstructor";
    }

    @PostMapping("{companyId}/saveInstructor")
    public String saveInstructor(@PathVariable("companyId")Long id,
                                  @ModelAttribute("newInstructor")Instructor instructor) {
        instructorService.saveInstructor(id,instructor);
        return "redirect:/instructors/allInstructors/ " + id;
    }

    @GetMapping("/getInstructor/{id}")
    public String getInstructorById(@PathVariable("id")Long id,Model model) {
        model.addAttribute("id",instructorService.getInstructorById(id));
        return "instructor/mainInstructor";
    }

    @GetMapping("/updateInstructor/{instructorId}")
    public String updateInstructor(@PathVariable("instructorId")Long instructorId,Model model) {
        Instructor instructor = instructorService.getInstructorById(instructorId);
        model.addAttribute("instructor",instructor);
        model.addAttribute("companyId",instructor.getCompany().getCompanyId());
        return "instructor/updateInstructor";
    }

    @PostMapping("/{companyId}/{instructorId}/saveUpdate")
    public String  saveUpdate(@PathVariable("companyId")Long companyId,
                               @PathVariable("instructorId")Long id,
                               @ModelAttribute("instructor")Instructor instructor) {
        instructorService.updateInstructor(id,instructor);
        return "redirect:/instructors/allInstructors/ "+ companyId;
    }

    @PostMapping("/{id}/{instructorId}/delete")
    public String deleteInstructor(@PathVariable("id")Long id,@PathVariable("instructorId")Long instructorId) {
        instructorService.deleteInstructorById(instructorId);
        return "redirect:/instructors/allInstructors/ " + id;
    }

}
