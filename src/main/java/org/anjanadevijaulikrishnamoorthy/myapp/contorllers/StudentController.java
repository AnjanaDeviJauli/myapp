package org.anjanadevijaulikrishnamoorthy.myapp.contorllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.anjanadevijaulikrishnamoorthy.myapp.dao.StudentRepoI;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.anjanadevijaulikrishnamoorthy.myapp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Controller
@RequestMapping(value = "students")
public class StudentController {
    StudentRepoI studentRepoI;
    StudentService studentService;
    @Autowired
    public StudentController(StudentRepoI studentRepoI,StudentService studentService) {
        this.studentRepoI = studentRepoI;
        this.studentService=studentService;
    }

    //Display all studentd to front end
    @GetMapping(value = {"/list"})
    public String findallstudents(Model model){
        List<Student> allStud = studentService.findAllStudents();
        int numberOfGirls = studentService.findNumberOfGirls();
        int numberOfBoys = studentService.findNumberOfBoys();
        model.addAttribute("allstu", allStud);
        model.addAttribute("numofgirls", numberOfGirls);
        model.addAttribute("numofboys", numberOfBoys);
        model.addAttribute("totalcount",numberOfBoys+numberOfGirls);
        model.addAttribute("grade", "All Students");
        return "list";
    }
    @GetMapping(value = {"/first"})
    public String firstgrade(Model model){
        studentService.byGrade(model,1);
        return "list";
    }
    @GetMapping(value = {"/second"})
    public String secondgrade(Model model){
        studentService.byGrade(model,2);
        return "list";
    }
    @GetMapping(value = {"/third"})
    public String thirdgrade(Model model){
     studentService.byGrade(model,3);
        return "list";
    }
    @GetMapping(value = {"/fourth"})
    public String fourthgrade(Model model){
    studentService.byGrade(model,4);
        return "list";
    }

    @GetMapping(value = {"/birthdaystoday"})
    public String birthday(Model model){
        List<Student> birthdaysToday=studentService.findStudentsHavingBirthdayToday();
        model.addAttribute("allstu", birthdaysToday);
        model.addAttribute("message","Happy Birthday!!!");
        return "birthdaystoday";
    }


    @ResponseBody
    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents(){
        return studentService.findAllStudents();
    }
    //Student form
    //Create student object
    @GetMapping("/studentform")
    public ModelAndView studentForm(){
        ModelAndView mv = new ModelAndView("studentform");
        Student s = new Student();
        mv.addObject("student",s);
        return mv;

    }
    //Save student object from student form
    @PostMapping("/savestudent")
    public String studentProcess(@Valid @ModelAttribute("student") Student students, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            log.debug(bindingResult.getAllErrors().toString());
            return "studentform";
        }
        log.warn("student process method" + students);
        studentRepoI.save(students);
        model.addAttribute("inserted","Sucessfully registered the student");
        return "studentform";
    }

@PostMapping("/findstudentbyId")
public String findStudentById(@RequestParam(required = false) int id, Model model){

    try {
        model.addAttribute("stu", studentService.findStudentById(id));

        log.warn(studentService.findStudentById(id).toString());
    } catch (RuntimeException ex){
        ex.printStackTrace();
        model.addAttribute("student_not_found",String.format("Student: %d not found!",id));
        return ("studentfind");
    }
    return "studentfind";
}
@PostMapping("/findStudentbyParam{id}")
public String findStudent(@RequestParam(required = true) int id1, Model model){
    model.addAttribute("stu", studentService.findStudentById(id1));
    return "studentfind";
}


    @PostMapping("/findstudentbyName")
    public String findStudentByName(@RequestParam(name="firstName") String firstName,
                              @RequestParam(name="lastName") String lastName,Model model){


        try {
            int id =studentService.findStudentIdByFirstAndLastName(firstName,lastName);
            model.addAttribute("stu",  studentService.findStudentById(id));
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
       Student s= studentService.findStudentById(id);
       log.warn(s.getDob().toString());
       mv.addObject("student",s);
       return mv;

   }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam int id){
        studentService.deleteStudentById(id);
        return "redirect:/students/list";
    }







}
