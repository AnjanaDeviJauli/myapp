package org.anjanadevijaulikrishnamoorthy.myapp.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.StudentRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackOn = {DataAccessException.class})
public class StudentService {
    StudentRepoI studentRepoI;
    @Autowired
    public StudentService(StudentRepoI studentRepoI) {
        this.studentRepoI = studentRepoI;
    }

    public Student findStudentById(int id){
        return studentRepoI.findById(id).get();
    }

   public List<Student> findAllStudents(){
        return studentRepoI.findAll();
   }

   public int findNumberOfGirls(){
        return studentRepoI.fineNumberOfGirls();
   }

   public int findNumberOfBoys(){
        return studentRepoI.fineNumberOfBoys();
   }

    public void byGrade(Model model, int gradeLevel){
        List<Student> students = studentRepoI.findByGrade(gradeLevel);
        int numberOfBoys = studentRepoI.fineNumberOfBoysInGrade(gradeLevel);
        int numberOfGirls = studentRepoI.fineNumberOfGirlsInGrade(gradeLevel);
        //log.warn(fourthgradestudents.toString());

        model.addAttribute("allstu", students);
        model.addAttribute("numofgirls", numberOfGirls);
        model.addAttribute("numofboys", numberOfBoys);
        model.addAttribute("totalcount",numberOfBoys+numberOfGirls);
        if(gradeLevel==1){
            model.addAttribute("grade", "First Grade Students");
        }else if(gradeLevel==2){
            model.addAttribute("grade", "Second Grade Students");
        }else if(gradeLevel==3){
            model.addAttribute("grade", "Third Grade Students");
        }else if(gradeLevel==4){
            model.addAttribute("grade", "Fourth Grade Students");
        }

    }

    public List<Student> findStudentsHavingBirthdayToday(){
        List<Student> birthdaysToday =new ArrayList<>();
        List<Student> allStudents = studentRepoI.findAll();
        LocalDate currentDate = LocalDate.now();
        // get current date and month
        int date = currentDate.getDayOfMonth();
        Month month = currentDate.getMonth();

        for(Student s : allStudents){
            if(s.getDob().getDayOfMonth()==date && s.getDob().getMonth()==month ){
                birthdaysToday.add(s);
            }
        }
        return  birthdaysToday;
    }

    public int findStudentIdByFirstAndLastName(String firstName, String lastName) {
      return   studentRepoI.findByFirstNameAndLastName(firstName, lastName).getId();
    }

    public void deleteStudentById(int id){
        studentRepoI.deleteById(id);
    }
}
