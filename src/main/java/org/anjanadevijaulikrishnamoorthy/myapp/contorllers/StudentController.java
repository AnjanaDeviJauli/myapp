package org.anjanadevijaulikrishnamoorthy.myapp.contorllers;

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

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "students")
public class StudentController {
    static final String STUDENTFORM="studentform";
    static final String STUDENTFIMD = "studentfind";
    static final String LIST = "list";
    static final String BIRTHDAYSTODAY = "birthdaystoday";
    final StudentRepoI studentRepoI;
    final StudentService studentService;

    @Autowired
    public StudentController(StudentRepoI studentRepoI, StudentService studentService) {
        this.studentRepoI = studentRepoI;
        this.studentService = studentService;
    }


    //Display all studentd to front end
    @GetMapping(value = {"/list"})
    public String findallstudents(Model model) {
        List<Student> allStud = studentService.findAllStudents();
        int numberOfGirls = studentService.findNumberOfGirls();
        int numberOfBoys = studentService.findNumberOfBoys();
        log.warn(allStud.toString());
        log.warn("number of boys" + numberOfBoys);
        log.warn("number of girls" + numberOfGirls);
        model.addAttribute("allstu", allStud);
        model.addAttribute("numofgirls", numberOfGirls);
        model.addAttribute("numofboys", numberOfBoys);
        model.addAttribute("totalcount", numberOfBoys + numberOfGirls);
        model.addAttribute("grade", "All Students");
        return LIST;
    }

    //display students in first grade
    @GetMapping(value = {"/first"})
    public String firstgrade(Model model) {
        studentService.byGrade(model, 1);
        return LIST;
    }

    //display students in second grade
    @GetMapping(value = {"/second"})
    public String secondgrade(Model model) {
        studentService.byGrade(model, 2);
        return LIST;
    }

    //display students in third grade
    @GetMapping(value = {"/third"})
    public String thirdgrade(Model model) {
        studentService.byGrade(model, 3);
        return LIST;
    }

    //dislplay students in fourth grade
    @GetMapping(value = {"/fourth"})
    public String fourthgrade(Model model) {
        studentService.byGrade(model, 4);
        return LIST;
    }

    //display students having bithday today
    @GetMapping(value = {"/birthdaystoday"})
    public String birthday(Model model) {
        List<Student> birthdaysToday = studentService.findStudentsHavingBirthdayToday();
        log.warn("students celebrating birhtday" + birthdaysToday.toString());
        model.addAttribute("allstu", birthdaysToday);
        model.addAttribute("message", "Happy Birthday!!!");
        return BIRTHDAYSTODAY;
    }


    //Example for rest controller
    @ResponseBody
    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }

    //Student form
    //Create student object
    @GetMapping("/studentform")
    public ModelAndView studentForm() {
        ModelAndView mv = new ModelAndView(STUDENTFORM);
        Student s = new Student();
        mv.addObject("student", s);
        return mv;

    }

    //Using student object above "student" to get values from fields and save in database
    @PostMapping("/savestudent")
    public String studentProcess(@Valid @ModelAttribute("student") Student students, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.debug(bindingResult.getAllErrors().toString());
            return STUDENTFORM;
        }
        log.warn("student save method" + students);
        //save distinct new student
        if (!studentService.findAllStudents().contains(students)) {
            studentRepoI.save(students);
            model.addAttribute("inserted", "Sucessfully registered/updated the student");
        } else {
            model.addAttribute("inserted", "Student already exist");
        }
        return STUDENTFORM;
    }

    //search student by id
    @PostMapping("/findstudentbyId")
    public String findStudentById(@RequestParam(required = false) int id, Model model) {

        try {
            model.addAttribute("stu", studentService.findStudentById(id));
            log.warn(studentService.findStudentById(id).toString());
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            model.addAttribute("student_not_found", String.format("Student: %d not found!", id));
            return STUDENTFIMD;
        }
        return STUDENTFIMD;
    }


//    @PostMapping("/findStudentbyParam{id}")
//    public String findStudent(@RequestParam(required = true) int id1, Model model) {
//        model.addAttribute("stu", studentService.findStudentById(id1));
//        return studentfind;
//    }


    //find student by firstname and lastname combination
    @PostMapping("/findstudentbyName")
    public String findStudentByName(@RequestParam(name = "firstName") String firstName,
                                    @RequestParam(name = "lastName") String lastName, Model model) {


        try {
            int id = studentService.findStudentIdByFirstAndLastName(firstName, lastName);
            model.addAttribute("stu", studentService.findStudentById(id));
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            model.addAttribute("student_not_found", "Student not found for the given first and last name combination!");
            return (STUDENTFIMD);
        }
        return STUDENTFIMD;
    }


    //mapping to student find page when it is refreshed
    @GetMapping("/find*")
    public String findStudent() {
        return STUDENTFIMD;
    }


    //to display the previous value of the student when updating
    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam int id) {
        ModelAndView mv = new ModelAndView("studentform");
        Student s = studentService.findStudentById(id);
        log.warn(s.toString()+"student is updated");
        mv.addObject("student", s);
        return mv;

    }

    //deleting a student
    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam int id) {
        studentService.deleteStudentById(id);
        log.debug("student with id: %d deleted",id);
        return "redirect:/students/list";
    }


}
