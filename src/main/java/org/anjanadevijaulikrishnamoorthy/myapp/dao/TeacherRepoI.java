package org.anjanadevijaulikrishnamoorthy.myapp.dao;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepoI extends JpaRepository<Teachers,Integer> {
    @Query("SELECT T.courses FROM Teachers T")
    List<Course> findTeachersCourses(int id);

    Optional<Teachers> findByEmail(String email);
}
