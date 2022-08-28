package mvc.repositoryImpl;

import mvc.model.Lesson;
import mvc.model.Video;
import mvc.repository.VideoRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class VideoRepositoryImpl implements VideoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveVideo(Long lessonId, Video video) {
        Lesson lesson = entityManager.find(Lesson.class,lessonId);
        lesson.setVideo(video);
        video.setLesson(lesson);
        entityManager.persist(video);
    }
    @Override
    public void updateVideo(Long id,Video video) {
        Video video1 = entityManager.find(Video.class,id);
        video1.setVideoName(video.getVideoName());
        video1.setLink(video.getLink());
        entityManager.merge(video1);
    }

    @Override
    public Video getVideoById(Long id) {
        return entityManager.find(Video.class,id);
    }


    @Override
    public List<Video> getAllVideos(Long id) {
        return entityManager.createQuery("SELECT v FROM Video v where v.lesson.lessonId = : id",Video.class)
                .setParameter("id",id).getResultList();
    }

    @Override
    public void deleteVideoById(Long id) {
        Video video = entityManager.find(Video.class,id);
        video.setLesson(null);
        entityManager.remove(video);
    }
}
