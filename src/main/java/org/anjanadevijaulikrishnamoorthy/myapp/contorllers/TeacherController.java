package org.anjanadevijaulikrishnamoorthy.myapp.contorllers;

import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.CourseRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.TeacherRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dto.TeacherDTO;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.anjanadevijaulikrishnamoorthy.myapp.services.CourseService;
import org.anjanadevijaulikrishnamoorthy.myapp.services.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping(value = "teachers")
public class TeacherController {
    private final CourseRepoI courseRepoI;

    final TeacherRepoI teacherRepoI;
   final TeacherService teacherService;
   final CourseService courseService;


    public TeacherController(TeacherRepoI teacherRepoI, TeacherService teacherService,
                             CourseRepoI courseRepoI,CourseService courseService) {
        this.teacherRepoI = teacherRepoI;
        this.teacherService = teacherService;
        this.courseRepoI = courseRepoI;
        this.courseService=courseService;
    }

    //Teacher form
    //Create teacher object
    @GetMapping("/teacherform")
    public String teacherForm(Model model){
        model.addAttribute("teacher", new Teachers());

        log.warn("teacher form method");
        return "teacherform";
    }
    //save course object by getting values from course form
    @PostMapping("/saveteacher")
    public String teacherProcess(@ModelAttribute("teacher") Teachers teacher){
        log.warn("teacher process method" + teacher);
        //log.warn(students.toString());
        teacherRepoI.save(teacher);
        return "teacherform";
    }

    //Display available course to front end
    @GetMapping(value = {"/teacherlist"})
    public String findallteachers(Model model){
        List<TeacherDTO> teachers = teacherService.getTeachersEssInfo();

        model.addAttribute("allteachers", teachers);
        return "teacherlist";
    }
    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam int id){
        ModelAndView mv = new ModelAndView("teacherform");
        Teachers s= teacherRepoI.findById(id).get();

        mv.addObject("teacher",s);
        return mv;

    }

    @GetMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam int id){
        teacherRepoI.deleteById(id);
        return "redirect:/teachers/teacherlist";
    }


    @GetMapping("/registertocourse/{id}")
    public String registerStudentToCourse(@PathVariable int id, Model model){
        model.addAttribute("teacher",teacherRepoI.findById(id));
        // courses available to register
        Set<Course> teacherNotRegisteredToThisCourses = new HashSet<>(courseRepoI.findAll());
        teacherNotRegisteredToThisCourses.removeAll(courseRepoI.findCourseAssignedToTeacher(id));
        log.info(teacherNotRegisteredToThisCourses.toString());
        model.addAttribute("teacherNotRegisteredToThisCourses",teacherNotRegisteredToThisCourses);
        return "teacherlist";
    }


}
