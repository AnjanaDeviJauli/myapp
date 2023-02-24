package org.anjanadevijaulikrishnamoorthy.myapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@ToString
@RequiredArgsConstructor
@Getter
@Setter
@Slf4j
@Entity
@Table(name = "scores")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Score {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "student_student_id")
    @NonNull
    //@OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    Student student;
    @ManyToOne
    @JoinColumn(name = "course_course_id")
    @NonNull
    //@OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    Course course;
    @NonNull
    double mark;


}
