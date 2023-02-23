package org.anjanadevijaulikrishnamoorthy.myapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor

@RequiredArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@Slf4j
@Entity

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Score {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NonNull
    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    Student student;
    @NonNull
    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    Course course;
    @NonNull
    double mark;


}
