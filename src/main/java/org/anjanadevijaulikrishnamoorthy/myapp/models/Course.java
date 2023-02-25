package org.anjanadevijaulikrishnamoorthy.myapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dto.TeacherDTO;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
    @NonNull
    @Column(name = "course_name", length = 48)
    String courseName;

//    @ToString.Exclude
//    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "courses",cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
//    Set<Student> students = new LinkedHashSet<>();

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "teacher_courses",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<Teachers> teachers = new LinkedHashSet<>();


//    public Set<TeacherDTO> getTeacherDTO() {
//        Set<TeacherDTO> teacher= new LinkedHashSet<>();
//        for (Teachers t:teachers){
//            teacher.add(new TeacherDTO(t.getId(),t.getFirstNameT(),t.getLastNameT(),t.getEmail(),t.getUsername(),t.getCourses()));
//        }
//        return teacher;
//
//    }


    }







