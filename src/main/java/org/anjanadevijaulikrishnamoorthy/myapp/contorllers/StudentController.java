package org.anjanadevijaulikrishnamoorthy.myapp.contorllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.anjanadevijaulikrishnamoorthy.myapp.dao.StudentRepoI;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@Controller
public class StudentController {
    StudentRepoI studentRepoI;
    @Autowired
    public StudentController(StudentRepoI studentRepoI) {
        this.studentRepoI = studentRepoI;
    }

    @GetMapping(value = {"/list"})
    public String findallstudents(Model model){
        List<Student> allStud = studentRepoI.findAll();
        int numberOfGirls = studentRepoI.fineNumberOfGirls();
        int numberOfBoys = studentRepoI.fineNumberOfBoys();
        model.addAttribute("allstu", allStud);
        model.addAttribute("numofgirls", numberOfGirls);
        model.addAttribute("numofboys", numberOfBoys);
        model.addAttribute("totalcount",numberOfBoys+numberOfGirls);
        return "list";
    }
    @GetMapping(value = {"/first"})
    public String firstgrade(Model model){
        byGrade(model,1);
        return "list";
    }
    @GetMapping(value = {"/second"})
    public String secondgrade(Model model){
        byGrade(model,2);
        return "list";
    }
    @GetMapping(value = {"/third"})
    public String thirdgrade(Model model){
     byGrade(model,3);
        return "list";
    }
    @GetMapping(value = {"/fourth"})
    public String fourthgrade(Model model){
    byGrade(model,4);
        return "list";
    }
    public void byGrade(Model model,int gradeLevel){
        List<Student> students = studentRepoI.findByGrade(gradeLevel);
        int numberOfBoys = studentRepoI.fineNumberOfBoysInGrade(gradeLevel);
        int numberOfGirls = studentRepoI.fineNumberOfGirlsInGrade(gradeLevel);
        //log.warn(fourthgradestudents.toString());

        model.addAttribute("allstu", students);
        model.addAttribute("numofgirls", numberOfGirls);
        model.addAttribute("numofboys", numberOfBoys);
        model.addAttribute("totalcount",numberOfBoys+numberOfGirls);

    }



    @GetMapping(value = {"/birthdaystoday"})
    public String birthday(Model model){
        List<Student> birthdaysToday = studentRepoI.birhthdaysToday();
        //log.warn(birthdaysToday.toString());

        model.addAttribute("allstu", birthdaysToday);
        return "birthdaystoday";
    }


    @ResponseBody
    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents(){
        return studentRepoI.findAll();
    }

    @GetMapping("/studentform")
    public String studentForm(Model model){
        model.addAttribute("student", new Student());

        log.warn("student form method");
        return "studentform";
    }

    @PostMapping("/s")
    public String studentProcess(@ModelAttribute("student") Student students){
        log.warn("student process method" + students);
        //log.warn(students.toString());
        studentRepoI.save(students);
        return "studentform";
    }
//    @GetMapping("/search")
//    public String requestParam(@RequestParam(value = "id") int id,Model model){
//       Student student= studentRepoI.getStudentById(id);
//        model.addAttribute("allstu", student);
//        return "list";
//    }
//
//    @GetMapping("/search")
//    public String requestParama(@RequestParam(value = "firstName") String firstName,
//                               @RequestParam(value = "lastName") String lastName,Model model){
//        Student student = studentRepoI.getStudent(firstName,lastName);
//        model.addAttribute("allstu", student);
//        return "birthdaystoday";
//    }






}
