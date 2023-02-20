package org.anjanadevijaulikrishnamoorthy.myapp.dao;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepoI extends JpaRepository<Student,Integer> {
    @Query("SELECT S FROM Student S WHERE S.grade=1")
    List<Student> findFirstGrade();

    @Query("SELECT S FROM Student S WHERE S.grade=2")
    List<Student> findSecondGrade();

    @Query("SELECT S FROM Student S WHERE S.grade=3")
    List<Student> findThirdGrade();

    @Query("SELECT S FROM Student S WHERE S.grade=4")
    List<Student> findFourthGrade();
}
