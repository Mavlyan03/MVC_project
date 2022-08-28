package mvc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
public class Video {
    @Id
    @SequenceGenerator(name = "video_seq",sequenceName = "video_seq",allocationSize = 1)
    @GeneratedValue(generator = "video_seq",strategy = GenerationType.SEQUENCE)
    private Long videoId;
    @Column(name = "video_name")
    private String videoName;
    @Column()
    private String link;

    @OneToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH})
    private Lesson lesson;

    public Video(String videoName, String link) {
        this.videoName = videoName;
        this.link = link;
    }
}
