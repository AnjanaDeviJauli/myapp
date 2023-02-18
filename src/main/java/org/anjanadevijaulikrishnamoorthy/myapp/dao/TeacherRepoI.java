package org.anjanadevijaulikrishnamoorthy.myapp.dao;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepoI extends JpaRepository<Teachers,Integer> {
}
