package org.anjanadevijaulikrishnamoorthy.myapp.dao;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepoI extends JpaRepository<Course,Integer> {
}
