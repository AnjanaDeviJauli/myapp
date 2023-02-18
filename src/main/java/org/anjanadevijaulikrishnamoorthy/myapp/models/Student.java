package org.anjanadevijaulikrishnamoorthy.myapp.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@RequiredArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@Slf4j
@Entity
@Table(name = "students")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NonNull
    @Column(length = 48, name="first_name")
    String firstName;
    @NonNull
    @Column(length = 48,name="last_name")
    String lastName;
    @NonNull @Column(name="dob")
    LocalDate dob;
    @NonNull
    char gender;
    @NonNull
    int grade;

//    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
//    @JoinTable(name = "student_courses",
//            joinColumns = @JoinColumn(name = "course_id"),
//    inverseJoinColumns = @JoinColumn(name="student_id"))
//    Set<Student> courses = new LinkedHashSet<>();

//    @ToString.Exclude
//    @ManyToMany(fetch = FetchType.EAGER,
//            cascade = {CascadeType.PERSIST,
//                    CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
//    @JoinTable(name = "student_teachers",
//            joinColumns = @JoinColumn(name = "teacher_id"),
//            inverseJoinColumns = @JoinColumn(name="student_id"))
//    Set<Teacher> teachers = new LinkedHashSet<>();



}
