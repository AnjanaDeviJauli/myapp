package org.anjanadevijaulikrishnamoorthy.myapp.dao;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.hibernate.annotations.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CourseRepoITest {
    CourseRepoI courseRepoI;
    TeacherRepoI teacherRepoI;
    @Autowired
    public CourseRepoITest(CourseRepoI courseRepoI,TeacherRepoI teacherRepoI) {

        this.courseRepoI = courseRepoI;
        this.teacherRepoI=teacherRepoI;
    }
    Course math= new Course("Mathemetics");


    @Test
    void testFindByCourseName(){
        assertThat(courseRepoI.findByCourseName("Mathemetics").get().equals(math)) ;
    }





}