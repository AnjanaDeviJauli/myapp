package org.anjanadevijaulikrishnamoorthy.myapp.services;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest
class StudentServiceTest {

    StudentService studentService;

    @Autowired
    public StudentServiceTest(StudentService studentService) {
        this.studentService = studentService;
    }
    Student s3 = (new Student("Kelly", "Duncan",
            LocalDate.of(2015, 4, 20), 'F', 1));
    Student s1 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2015,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), 'M', 1));
    @Test
    void findStudentById() {
        assertThat(studentService.findStudentById(3)).isEqualTo(s3);

    }

    @Test
    void findStudentHavingBirthdayToday(){
        assertThat(studentService.findStudentsHavingBirthdayToday().get(0)).isEqualTo(s1);
    }
}