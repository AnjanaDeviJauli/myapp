package org.anjanadevijaulikrishnamoorthy.myapp.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.CourseRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.ScoreRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.StudentRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.TeacherRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
@Service @Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackOn = {DataAccessException.class})
public class TeacherService {
    StudentRepoI studentRepoI;
    CourseRepoI courseRepoI;
     TeacherRepoI teacherRepoI;
     ScoreRepoI scoreRepoI;


    @Autowired
    public TeacherService(StudentRepoI studentRepoI,
                          CourseRepoI courseRepoI,
                          TeacherRepoI teacherRepoI,
                          ScoreRepoI scoreRepoI
    ) {
        this.studentRepoI = studentRepoI;
        this.courseRepoI = courseRepoI;
        this.teacherRepoI = teacherRepoI;
        this.scoreRepoI=scoreRepoI;

    }

    @Transactional(rollbackOn = {NoSuchElementException.class})
    public void addCourse(Integer id, Course c) throws NoSuchElementException{

        Teachers t = teacherRepoI.findById(id).orElseThrow();
        c = courseRepoI.save(c);
        t.addCourses(c);
        teacherRepoI.save(t);
    }
}
