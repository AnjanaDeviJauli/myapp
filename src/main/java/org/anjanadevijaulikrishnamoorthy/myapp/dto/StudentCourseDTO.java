package org.anjanadevijaulikrishnamoorthy.myapp.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;

import java.util.List;
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class StudentCourseDTO {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @NonNull
    Student student;
    @NonNull
    String course;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getCourse() {
        return course;
    }

    public void setCourseList(String course) {
        this.course = course;
    }
}
