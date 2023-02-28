package org.anjanadevijaulikrishnamoorthy.myapp.contorllers;

import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.CourseRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dto.CourseDTO;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.anjanadevijaulikrishnamoorthy.myapp.services.CourseService;
import org.anjanadevijaulikrishnamoorthy.myapp.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "courses")
public class CourseController {


    CourseRepoI courseRepoI;
    CourseService courseService;
    @Autowired
    public CourseController(CourseRepoI courseRepoI,CourseService courseService) {
        this.courseRepoI = courseRepoI;
        this.courseService =courseService;
    }

    //Course form
    //Create course object
    @GetMapping("/courseform")
    public String courseForm(Model model){
        model.addAttribute("course", new Course());

        log.warn("course form method");
        return "courseform";
    }
    //save course object by getting values from course form
    @PostMapping("/savecourse")
    public String courseProcess(@ModelAttribute("course") Course course){
        log.warn("course process method" + course);
        //log.warn(students.toString());
        courseRepoI.save(course);
        return "courseform";
    }


    //Display available course to front end
    @GetMapping(value = {"/courselist"})
    public String findallcourses(Model model){
        List<CourseDTO> courses = courseService.getCourseEssInfo();

        model.addAttribute("allcourses", courses);
        return "courselist";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam int id){
        ModelAndView mv = new ModelAndView("courseform");
        Course s= courseRepoI.findById(id).get();

        mv.addObject("course",s);
        return mv;

    }

    @GetMapping("/deleteCourse")
    public String deleteCourse(@RequestParam int id){
        courseRepoI.deleteById(id);
        return "redirect:/courses/courselist";
    }
}
