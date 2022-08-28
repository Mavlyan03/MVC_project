package mvc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {
    @Id
    @SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
    @GeneratedValue(generator = "course_seq", strategy = GenerationType.SEQUENCE)
    private Long courseId;

    @JoinColumn(name = "course_name")
    private String course_name;

    @Column(name = "date_of_start")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfStart;
    @Column
    private int duration;
    @Column
    private String image;
    @Column
    private String description;

    @ManyToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH}, mappedBy = "courses")
    private List<Instructor> instructors;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Student> students;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courses")
    private List<Lesson> lessons;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH,
            CascadeType.DETACH})
    private Company company;

    public void addInstructors(Instructor instructor) {
        if (instructors == null) {
            instructors = new ArrayList<>();
        } else {
            this.instructors.add(instructor);
        }
    }

    public void addStudents(Student student) {
        if (students == null) {
            students = new ArrayList<>();
        } else {
            this.students.add(student);
        }
    }

    public void addLessons(Lesson lesson) {
        if (lessons == null) {
            lessons = new ArrayList<>();
        } else {
            this.lessons.add(lesson);
        }
    }

    public Course(LocalDate dateOfStart, int duration, String image, String description) {
        this.dateOfStart = dateOfStart;
        this.duration = duration;
        this.image = image;
        this.description = description;
    }
}
