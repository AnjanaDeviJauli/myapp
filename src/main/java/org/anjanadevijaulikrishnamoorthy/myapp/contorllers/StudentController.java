package org.anjanadevijaulikrishnamoorthy.myapp.contorllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.anjanadevijaulikrishnamoorthy.myapp.dao.StudentRepoI;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
@Slf4j
@Controller
@RequestMapping(value = "students")
public class StudentController {
    StudentRepoI studentRepoI;
    @Autowired
    public StudentController(StudentRepoI studentRepoI) {
        this.studentRepoI = studentRepoI;
    }

    //Display all studentd to front end
    @GetMapping(value = {"/list"})
    public String findallstudents(Model model){
        List<Student> allStud = studentRepoI.findAll();
        int numberOfGirls = studentRepoI.fineNumberOfGirls();
        int numberOfBoys = studentRepoI.fineNumberOfBoys();
        model.addAttribute("allstu", allStud);
        model.addAttribute("numofgirls", numberOfGirls);
        model.addAttribute("numofboys", numberOfBoys);
        model.addAttribute("totalcount",numberOfBoys+numberOfGirls);
        model.addAttribute("grade", "All Students");
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
    //Student form
    //Create student object
    @GetMapping("/studentform")
    public ModelAndView studentForm(){
//        model.addAttribute("student", new Student());
//
//        log.warn("student form method");
//        return "studentform";
        ModelAndView mv = new ModelAndView("studentform");
        Student s = new Student();
        mv.addObject("student",s);
        return mv;

    }
    //Save student object from student form
    @PostMapping("/savestudent")
    public String studentProcess(@ModelAttribute("student") Student students){
        log.warn("student process method" + students);
        //log.warn(students.toString());
        studentRepoI.save(students);

        return "studentform";
    }

@PostMapping("/findstudentbyId")
public String findStudentById(@RequestParam(required = false) int id,@PathVariable(required = false) int id1, Model model){

    try {
        model.addAttribute("stu", studentRepoI.findById(id).get());
        model.addAttribute("stu", studentRepoI.findById(id1).get());
        log.warn(studentRepoI.findById(id).get().toString());
    } catch (RuntimeException ex){
        ex.printStackTrace();
        model.addAttribute("student_not_found",String.format("Student: %d not found!",id));
        return ("studentfind");
    }
    return "studentfind";
}
@PostMapping("/findStudentbyParam{id}")
public String findStudent(@PathVariable(required = true) int id1, Model model){
    model.addAttribute("stu", studentRepoI.findById(id1).get());
    return "studentfind";
}


    @PostMapping("/findstudentbyName")
    public String findStudentByName(@RequestParam(name="firstName") String firstName,
                              @RequestParam(name="lastName") String lastName,Model model){


        try {
            int id =studentRepoI.findByFirstNameAndLastName(firstName,lastName).getId();
            model.addAttribute("stu",  studentRepoI.findById(id).get());
            log.warn(studentRepoI.findByFirstNameAndLastName(firstName,lastName).toString());
        } catch (RuntimeException ex){
            ex.printStackTrace();
            model.addAttribute("student_not_found",String.format("Student not found for the given first and last name combination!"));
            return ("studentfind");
        }
        return "studentfind";
    }
   @GetMapping("/find*")
    public String findStudent(){
        return  "studentfind";
   }

   @GetMapping("/showUpdateForm")
   public ModelAndView showUpdateForm(@RequestParam int id){
        ModelAndView mv = new ModelAndView("studentform");
       Student s= studentRepoI.findById(id).get();
       log.warn(s.getDob().toString());
       mv.addObject("student",s);
       return mv;

   }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam int id){
        studentRepoI.deleteById(id);
        return "redirect:/students/list";
    }





}
