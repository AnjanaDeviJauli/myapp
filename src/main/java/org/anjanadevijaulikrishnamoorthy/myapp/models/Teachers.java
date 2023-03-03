package org.anjanadevijaulikrishnamoorthy.myapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
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
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "teachers", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH,CascadeType.REMOVE})
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


    public void addCourses(Course course) {
        courses.add(course);

        course.getTeachers().add(this);
    }
    public void deleteCourses(Course course) {
        courses.remove(course);

        course.getTeachers().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teachers teachers)) return false;
        return getId() == teachers.getId() && getFirstNameT().equals(teachers.getFirstNameT()) && getLastNameT().equals(teachers.getLastNameT()) && getEmail().equals(teachers.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstNameT(), getLastNameT(), getEmail());
    }
}
