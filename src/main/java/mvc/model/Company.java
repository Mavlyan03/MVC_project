package mvc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @SequenceGenerator(name = "company_seq",sequenceName = "company_seq",allocationSize = 1)
    @GeneratedValue(generator = "company_seq",strategy = GenerationType.SEQUENCE)
    private Long companyId;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "located_country")
    private String locatedCountry;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<Course> courses;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<Instructor> instructors;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<Student> students;

    public void addStudent(Student student) {
        if(students == null) {
            students=new ArrayList<>();
        } else {
            this.students.add(student);
        }
    }

    public void addCourse(Course course) {
        if(courses == null) {
            courses = new ArrayList<>();
        } else {
            this.courses.add(course);
        }
    }

    public void addInstructor(Instructor instructor) {
        if(instructors == null) {
            instructors = new ArrayList<>();
        } else {
            this.instructors.add(instructor);
        }
    }

    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }
}
