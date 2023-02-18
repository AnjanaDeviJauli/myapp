package org.anjanadevijaulikrishnamoorthy.myapp.dao;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepoI extends JpaRepository<Student,Integer> {
}
