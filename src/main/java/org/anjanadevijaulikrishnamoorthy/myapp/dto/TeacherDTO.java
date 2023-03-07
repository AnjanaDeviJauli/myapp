package org.anjanadevijaulikrishnamoorthy.myapp.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;

import java.util.List;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {

    int id;
    String firstNameT;

    String lastNameT;

    String email;

    String username;

    List<String> courses;

    @Override
    public String toString() {
        return
                "id=" + id +
                ", firstNameT='" + firstNameT + '\'' +
                ", lastNameT='" + lastNameT + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' ;
    }
}
