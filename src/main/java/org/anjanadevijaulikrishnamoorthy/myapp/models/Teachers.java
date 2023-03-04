package org.anjanadevijaulikrishnamoorthy.myapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

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
    @Length(min = 2,max = 50,message = "Minimum length is 2 and maximum length allowed is 50")
    @NonNull
    @Column(length = 48,name="first_name_t")
    String firstNameT;
    @Length(min = 2,max = 50,message = "Minimum length is 2 and maximum length allowed is 50")
    @NonNull
    @Column(length = 48,name = "last_name_t")
    String lastNameT;
    @Email(message = "please enter valid email address",regexp = ".+@.+\\..+")
    @NonNull
    @Column(length = 48)
    String email;
    @Length(min = 4,max = 50,message = "Maximum length of the username is 50 characters and minumum length is 4")
    @NonNull
    @Column(length = 48)
    String username;

    @NonNull
    @NotBlank(message="please enter valid password")
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
        return  getFirstNameT().equals(teachers.getFirstNameT()) && getLastNameT().equals(teachers.getLastNameT()) && getEmail().equals(teachers.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstNameT(), getLastNameT(), getEmail());
    }
}
