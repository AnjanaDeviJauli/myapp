package org.anjanadevijaulikrishnamoorthy.myapp.models;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.services.ScoreService;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@NoArgsConstructor
@ToString
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
    @Transient
    int age;



    public int getAge() {
        return Period.between(this.dob,LocalDate.now()).getYears();
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return getId() == student.getId() && getGender() == student.getGender() && getGrade() == student.getGrade() && getAge() == student.getAge() && getFirstName().equals(student.getFirstName()) && getLastName().equals(student.getLastName()) && getDob().equals(student.getDob());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getDob(), getGender(), getGrade(), getAge());
    }
}
