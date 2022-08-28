package mvc.api;

import mvc.model.Video;
import mvc.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/allVideos/{lessonId}")
    private String  getAllVideos(@PathVariable("lessonId")Long lessonId, Model model) {
        model.addAttribute("allVideos",videoService.getAllVideos(lessonId));
        model.addAttribute("lesson",lessonId);
        return "video/mainVideo";
    }

    @GetMapping("{lessonId}/newVideo")
    private String newVideo(@PathVariable("lessonId")Long id,Model model) {
        model.addAttribute("newVideo",new Video());
        model.addAttribute("lessonId",id);
        return "video/newVideo";
    }

    @PostMapping("{lessonId}/saveVideo")
    private String saveVideo(@PathVariable("lessonId")Long id, @ModelAttribute("newVideo")Video video) {
        videoService.saveVideo(id,video);
        return "redirect:/videos/allVideos/ "+id;
    }

    @GetMapping("/getVideo/{videoId}")
    private String getVideoById(@PathVariable("videoId")Long id,Model model) {
        model.addAttribute("video",videoService.getVideoById(id));
        return "video/mainVideo";
    }

    @GetMapping("/updateVideo/{videoId}")
    private String updateVideo(@PathVariable("videoId")Long id,Model model) {
        Video video = videoService.getVideoById(id);
        model.addAttribute("video",video);
        model.addAttribute("lessonId",video.getLesson().getLessonId());
        return "video/updateVideo";
    }

    @PostMapping("/{lessonId}/{videoId}/saveUpdateVideo")
    private String saveUpdateVideo(@PathVariable("lessonId")Long id,
                                   @PathVariable("videoId")Long videoId,
                                   @ModelAttribute("video")Video video) {
        videoService.updateVideo(videoId,video);
        return "redirect:/videos/allVideos/ " + id;
    }

    @PostMapping("/{lessonId}/{videoId}/deleteVideo")
    private String deleteVideo(@PathVariable("lessonId")Long id,@PathVariable("videoId")Long videoId) {
        videoService.deleteVideoById(videoId);
        return "redirect:/videos/allVideos/ " + id;
    }
}
