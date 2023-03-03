package org.anjanadevijaulikrishnamoorthy.myapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dto.TeacherDTO;
import org.hibernate.validator.constraints.Length;

import java.util.*;

@NoArgsConstructor
@ToString
@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
@Entity
@Table(name = "courses")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    int id;
    @Length(min = 2,max = 50,message = "Minimum length is 2 and maximum length is 50")
    @NonNull
    @Column(name = "course_name", length = 48)
    String courseName;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "teacher_courses",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<Teachers> teachers = new LinkedHashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        return getId() == course.getId() && getCourseName().equals(course.getCourseName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCourseName());
    }


    }







