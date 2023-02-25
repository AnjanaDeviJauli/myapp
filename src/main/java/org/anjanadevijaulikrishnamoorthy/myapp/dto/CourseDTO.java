package org.anjanadevijaulikrishnamoorthy.myapp.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    int id;
    String courseName;

    List<TeacherDTO> teachers;

    @Override
    public String toString() {
        return "CourseDTO{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", teachers=" + teachers +
                '}';
    }
}
