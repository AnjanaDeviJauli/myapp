package org.anjanadevijaulikrishnamoorthy.myapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Data
@RequiredArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@Slf4j
@Entity
@Table(name = "student_course_score")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentCourseScore {
    @Id
    @Column(name ="student_course_score_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name = "student_id")
    @NonNull
    Student student;
    @ManyToOne
    @JoinColumn(name = "course_id")
    @NonNull
    Course course;
    @NonNull
    int score;



}
