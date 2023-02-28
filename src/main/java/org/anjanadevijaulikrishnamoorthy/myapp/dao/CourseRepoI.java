package org.anjanadevijaulikrishnamoorthy.myapp.dao;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepoI extends JpaRepository<Course,Integer> {
    @Query("SELECT T.courses FROM Teachers T WHERE T.id=?1")
    List<Course> findCourseAssignedToTeacher(int id);

    @Query("SELECT C FROM Course C WHERE C.courseName=?1")
    Optional<Course> findByCourseName(String name);

    @Query("SELECT C.teachers FROM Course C WHERE C.id=?1")
    List<Teachers> findTeacherAssignedToCourse(int id);
}
