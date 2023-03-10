package org.anjanadevijaulikrishnamoorthy.myapp.dao;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;
@SpringBootTest
class TeacherRepoITest {
   TeacherRepoI teacherRepoI;
   @Autowired
    public TeacherRepoITest(TeacherRepoI teacherRepoI) {
        this.teacherRepoI = teacherRepoI;
    }

    Teachers t1 = new Teachers("Ashley","Minton",
            "ashley@gmail.com","ashleyminton","password");

    @Test
    void findByEmail() {
        assertThat(teacherRepoI.findByEmail("ashley@gmail.com")).contains(t1);
    }
}