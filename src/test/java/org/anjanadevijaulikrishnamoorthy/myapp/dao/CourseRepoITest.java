package org.anjanadevijaulikrishnamoorthy.myapp.dao;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CourseRepoITest {
    CourseRepoI courseRepoI;
    TeacherRepoI teacherRepoI;
    @Autowired
    public CourseRepoITest(CourseRepoI courseRepoI,TeacherRepoI teacherRepoI) {

        this.courseRepoI = courseRepoI;
        this.teacherRepoI=teacherRepoI;
    }
    Course math = new Course("Mathemetics");
    Course science = new Course("Science");
    Course language = new Course("English");
    Course social = new Course("Social");
    List<Course> allCourses = new ArrayList<>();

    Teachers t4 = new Teachers("Anjana","Devi",
            "anjana@gmail.com","anjanadevi","password");

    @Test
    void testFindByCourseName(){
        assertThat(courseRepoI.findByCourseName("Mathemetics")).contains(math) ;
    }
    @Test
    void testFindCourseAssignedToTeacher(){
          assertThat(courseRepoI.findCourseAssignedToTeacher(2).get(0).getCourseName())
                  .isEqualTo(science.getCourseName());
    }

    @Test
    void testFindTeacherAssignedToCourse(){
        assertThat(courseRepoI.findTeacherAssignedToCourse(3).get(0).getEmail())
                .isEqualTo(t4.getEmail());
    }

    @Test
    void testFindAll(){
        allCourses.add(math);
        allCourses.add(science);
        allCourses.add(social);
        allCourses.add(language);

       assertThat(courseRepoI.findAll()).isEqualTo(allCourses);

    }







}