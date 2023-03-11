package org.anjanadevijaulikrishnamoorthy.myapp.contorllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.CourseRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.TeacherRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dto.TeacherDTO;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;

import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.anjanadevijaulikrishnamoorthy.myapp.services.CourseService;
import org.anjanadevijaulikrishnamoorthy.myapp.services.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String teacherProcess(@Valid @ModelAttribute("teacher") Teachers teacher, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            log.debug(bindingResult.getAllErrors().toString());
            return "teacherform";
        }
        log.warn("teacher process method" + teacher);
        log.warn(teacher.toString()+"saved to teacher list");
        if(!teacherRepoI.findAll().contains(teacher)){
        teacherRepoI.save(teacher);
        model.addAttribute("inserted","Succesfully added the teacher");}
        else{model.addAttribute("inserted","Teacher already exist");}
        return "teacherform";
    }

    //Display available course to front end
    @GetMapping(value = {"/teacherlist"})
    public String findallteachers(Model model){
        List<TeacherDTO> teachers = teacherService.getTeachersEssInfo();
        model.addAttribute("allteachers", teachers);
        return "teacherlist";
    }

    //populate update form
    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam int id){
        ModelAndView mv = new ModelAndView("teacherform");
        Teachers s= teacherRepoI.findById(id).get();

        mv.addObject("teacher",s);
        return mv;

    }

    //delete teacher if no course assigned
    @GetMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam int id,Model model)  {
        Teachers t =teacherRepoI.findById(id).get();
        if(t.getCourses()!=null && t.getCourses().size()==0) {
            teacherRepoI.deleteById(id);
            log.warn("Teacher with id %d deleted",id);
            model.addAttribute("message1",String.format("%s %s Teacher is deleted",t.getFirstNameT(),t.getLastNameT()));
        }else{
            model.addAttribute("message1",
                    String.format("%s %s teacher cannot be deleted because, he/she is assigned to course",t.getFirstNameT(),t.getLastNameT()));

        }
        List<TeacherDTO> teachers = teacherService.getTeachersEssInfo();

        model.addAttribute("allteachers", teachers);

        return "teacherlist";
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
