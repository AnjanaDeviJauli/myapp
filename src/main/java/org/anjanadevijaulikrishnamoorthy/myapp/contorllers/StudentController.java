package org.anjanadevijaulikrishnamoorthy.myapp.contorllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.anjanadevijaulikrishnamoorthy.myapp.dao.StudentRepoI;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Slf4j
@Controller
public class StudentController {
    StudentRepoI studentRepoI;
    @Autowired
    public StudentController(StudentRepoI studentRepoI) {
        this.studentRepoI = studentRepoI;
    }

    @GetMapping(value = {"list"})
    public String findallstudents(Model model){
        List<Student> allStud = studentRepoI.findAll();

        model.addAttribute("allstu", allStud);
        return "list";
    }
    @GetMapping(value = {"first"})
    public String firstgrade(Model model){
        List<Student> firstgradestudents = studentRepoI.findFirstGrade();
        log.warn(firstgradestudents.toString());

        model.addAttribute("firstgrade", firstgradestudents);
        return "first";
    }
    @GetMapping(value = {"second"})
    public String secondgrade(Model model){
        List<Student> secondgradestudents = studentRepoI.findSecondGrade();
        model.addAttribute("secondgrade", secondgradestudents);
        return "second";
    }
    @GetMapping(value = {"third"})
    public String thirdgrade(Model model){
        List<Student> thirdgradestudents = studentRepoI.findThirdGrade();
        log.warn(thirdgradestudents.toString());

        model.addAttribute("thirdgrade", thirdgradestudents);
        return "third";
    }
    @GetMapping(value = {"fourth"})
    public String fourthgrade(Model model){
        List<Student> fourthgradestudents = studentRepoI.findFourthGrade();
        log.warn(fourthgradestudents.toString());

        model.addAttribute("fourthgrade", fourthgradestudents);
        return "fourth";
    }
    @ResponseBody
    @GetMapping("getAllStudents")
    public List<Student> getAllStudents(){
        return studentRepoI.findAll();
    }
}
