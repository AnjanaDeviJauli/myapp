package org.anjanadevijaulikrishnamoorthy.myapp.dao;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.anjanadevijaulikrishnamoorthy.myapp.models.StudentCourseScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseScoreRepoI extends JpaRepository<StudentCourseScore,Integer> {

}
