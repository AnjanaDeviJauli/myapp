package org.anjanadevijaulikrishnamoorthy.myapp.dao;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentRepoITest {

    StudentRepoI studentRepoI;

    @Autowired
    public StudentRepoITest(StudentRepoI studentRepoI) {
        this.studentRepoI = studentRepoI;
    }
    Student s1 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2015,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), 'M', 1));


    @Test
    void testFindByGrade() {
        assertThat(studentRepoI.findByGrade(1).get(0)).isEqualTo(s1);
    }

    @Test
    void findByFirstNameAndLastName() {
        assertThat(studentRepoI.findByFirstNameAndLastName("Ganesh","venkat")).isEqualTo(s1);
    }

    @Test
    void fineNumberOfBoys() {
        assertThat(studentRepoI.fineNumberOfBoys()).isEqualTo(15);
    }

    @Test
    void fineNumberOfGirls() {
        assertThat(studentRepoI.fineNumberOfGirls()).isEqualTo(15);
    }

    @Test
    void fineNumberOfBoysInGrade() {
        assertThat(studentRepoI.fineNumberOfBoysInGrade(1)).isEqualTo(3);
    }

    @Test
    void fineNumberOfGirlsInGrade() {
        assertThat(studentRepoI.fineNumberOfGirlsInGrade(1)).isEqualTo(4);
    }


}