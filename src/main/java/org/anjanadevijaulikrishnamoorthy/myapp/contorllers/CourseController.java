package org.anjanadevijaulikrishnamoorthy.myapp.contorllers;

import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.CourseRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.TeacherRepoI;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "courses")
public class CourseController {
    private final TeacherRepoI teacherRepoI;


   private final CourseRepoI courseRepoI;
  private  final   CourseService courseService;
    private final TeacherService teacherService;

    @Autowired
    public CourseController(CourseRepoI courseRepoI,CourseService courseService,
                            TeacherRepoI teacherRepoI,TeacherService teacherService) {
        this.courseRepoI = courseRepoI;
        this.courseService =courseService;
        this.teacherRepoI = teacherRepoI;
        this.teacherService=teacherService;
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
    @PostMapping("{id}/savecoursestoteacher")
    public String saveCourseToTeacher(@RequestParam("courses-choice") String name, @PathVariable("id") int id, RedirectAttributes model){

        // check course is on the list
        boolean isCourseNameValid = courseRepoI.findAll().stream().anyMatch(course -> course.getCourseName().equals(name));
        if(isCourseNameValid){
            try {
                teacherService.addCourse(id, courseService.findCourseByName(name));
                model.addFlashAttribute("message", String.format("Persist %s to %d", name,id));
                log.info(String.format("Persist %s to %d", name,id));
            }catch(RuntimeException ex){
                model.addFlashAttribute("message", String.format("Couldn't persist %s to %d", name,id));
                log.error(String.format("Couldn't persist %s to %d", name,id));
                ex.printStackTrace();
            }
        } else {
            model.addFlashAttribute("message", String.format("Couldn't persist %s to %s because course don't exist", name,id));
            log.warn(String.format("Couldn't persist %s to %s because course doesn't exist", name,id));
        }
        log.info("courses-choice:" + name);
        log.info("isCourseNameValid: "+ isCourseNameValid);

        return "redirect:/teachers/teacherlist";
    }
}
