package org.anjanadevijaulikrishnamoorthy.myapp.dao;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Score;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ScoreRepoI extends JpaRepository<Score,Integer> {
    @Query("SELECT S FROM Score S WHERE S.student=?1")
    List<Score> findScoreByStudent(Student s);

    @Query("SELECT S.course from Score S WHERE S.student=?1")
    List<Course> findCourseByStudent(Student s);

    @Query("SELECT AVG(S.mark) FROM Score S WHERE S.student=?1")
    Double findStudentAverageScore(Student s);

}
