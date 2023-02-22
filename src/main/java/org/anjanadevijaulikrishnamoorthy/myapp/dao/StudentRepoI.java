package org.anjanadevijaulikrishnamoorthy.myapp.dao;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface StudentRepoI extends JpaRepository<Student,Integer> {


    @Query("SELECT S FROM Student S WHERE S.grade=?1")
    List<Student> findByGrade(int grade);



    @Query("SELECT count(S.gender) fROM Student S where S.gender='M'")
    Integer fineNumberOfBoys();

    @Query("SELECT count(S.gender) fROM Student S where S.gender='F'")
    Integer fineNumberOfGirls();

    @Query("SELECT count(S.gender) fROM Student S where S.gender='M' AND S.grade=?1")
    Integer fineNumberOfBoysInGrade(int grade);

    @Query("SELECT count(S.gender) fROM Student S where S.gender='F' AND S.grade=?1")
    Integer fineNumberOfGirlsInGrade(int grade);


    @Query("SELECT S FROM Student S WHERE S.dob=current_date")
    List<Student>  birhthdaysToday();
}
