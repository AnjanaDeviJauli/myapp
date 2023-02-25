package org.anjanadevijaulikrishnamoorthy.myapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@RequiredArgsConstructor
@ToString
//@AllArgsConstructor
@Getter
@Setter
@Slf4j
@Entity
@Table(name = "teachers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Teachers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NonNull
    @Column(length = 48,name="first_name_t")
    String firstNameT;
    @NonNull
    @Column(length = 48,name = "last_name_t")
    String lastNameT;
    @NonNull
    @Column(length = 48)
    String email;
    @NonNull
    @Column(length = 48)
    String username;
    @NonNull
    @Column(length = 48)
    String password;


    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "teachers", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    Set<Course> courses = new LinkedHashSet<>();

    public List<String> getCourses() {
        List<String> course= new ArrayList<>();
        for (Course c:courses){
            course.add(c.getCourseName());
        }
        return course;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    //    @ToString.Exclude
//    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "teachers",cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
//    Set<Student> students = new LinkedHashSet<>();

    public void addCourses(Course course) {
        courses.add(course);

        course.getTeachers().add(this);
    }


}
