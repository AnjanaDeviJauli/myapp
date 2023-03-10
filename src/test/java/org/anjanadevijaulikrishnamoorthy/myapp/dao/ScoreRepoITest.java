package org.anjanadevijaulikrishnamoorthy.myapp.dao;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Score;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class ScoreRepoITest {

    ScoreRepoI scoreRepoI;
    StudentRepoI studentRepoI;
@Autowired
    public ScoreRepoITest(ScoreRepoI scoreRepoI, StudentRepoI studentRepoI) {
        this.scoreRepoI = scoreRepoI;
        this.studentRepoI = studentRepoI;
    }

    Course science = new Course("Science");
    Student s1 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2015,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), 'M', 1));
    Score s = new Score(s1,science,70.00);
    @Test
    void testFindScoreByStudent() {
        assertThat(s.getStudent()).isEqualTo(s1);
        }
    @Test
    void findCourseByStudent() {
        assertThat(s.getCourse()).isEqualTo(science);
    }



    @Test
    void findStudentScoreSum() {
        assertThat(s.getMark()).isEqualTo(70);
    }
}