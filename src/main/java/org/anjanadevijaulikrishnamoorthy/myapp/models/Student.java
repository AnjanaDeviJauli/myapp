package org.anjanadevijaulikrishnamoorthy.myapp.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@NoArgsConstructor
@ToString
@RequiredArgsConstructor
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
    @Length(min = 2,max = 50,message = "Minimum length is 2 and maximum length allowed is 50")
    @NonNull
    @Column(length = 48, name="first_name")
    String firstName;
    @Length(min = 2,max = 50,message = "Minimum length is 2 and maximum length allowed is 50")
    @NonNull
    @Column(length = 48,name="last_name")
    String lastName;
    @Past(message = "Enter year that is in past")
    @NonNull @Column(name="dob")
    LocalDate dob;

    @NonNull
    @NotNull(message = "select one")
    char gender;
    @Range(min=1,max=4,message = "Minumum grade level is 1 and maximum grade level is 4")
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
        return  getGender() == student.getGender() && getGrade() == student.getGrade() && getAge() == student.getAge() && getFirstName().equals(student.getFirstName()) && getLastName().equals(student.getLastName()) && getDob().equals(student.getDob());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getDob(), getGender(), getGrade(), getAge());
    }
}
