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
    private final CourseService courseService;
    private final TeacherService teacherService;

    @Autowired
    public CourseController(CourseRepoI courseRepoI, CourseService courseService,
                            TeacherRepoI teacherRepoI, TeacherService teacherService) {
        this.courseRepoI = courseRepoI;
        this.courseService = courseService;
        this.teacherRepoI = teacherRepoI;
        this.teacherService = teacherService;
    }

    //Course form
    //Create course object
    @GetMapping("/courseform")
    public String courseForm(Model model) {
        model.addAttribute("course", new Course());

        log.warn("course form method");
        return "courseform";
    }

    //save course object by getting values from course form
    @PostMapping("/savecourse")
    public String courseProcess(@ModelAttribute("course") Course course) {
        log.warn("course process method" + course);
        //log.warn(students.toString());

        if (courseRepoI.findAll().stream().anyMatch(course1 -> course1.getId() == (course.getId()))) {
            Course c = courseRepoI.findById(course.getId()).get();
            c.setCourseName(course.getCourseName());
            courseRepoI.save(c);

        } else
            courseRepoI.save(course);
        return "courseform";
    }


    //Display available course to front end
    @GetMapping(value = {"/courselist"})
    public String findallcourses(Model model) {
        List<CourseDTO> courses = courseService.getCourseEssInfo();

        model.addAttribute("allcourses", courses);
        return "courselist";
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam int id) {
        ModelAndView mv = new ModelAndView("courseform");
        Course s = courseRepoI.findById(id).get();

        mv.addObject("course", s);
        return mv;

    }

    @GetMapping("/deleteCourse")
    public String deleteCourse(@RequestParam int id) {
        courseRepoI.deleteById(id);
        return "redirect:/courses/courselist";
    }

    //Delete course assigned to  Teacher
    @PostMapping ("{id}/deleteCourseFromTeacher")
    public String deleteCourseAssignedToTeacher(@RequestParam("course") String name,@PathVariable("id") int id,RedirectAttributes model){
        if(courseService.findIfCourseExist(name)){
        teacherService.deletCourse(id,name);
        String teacherFirstAndLastName=teacherService.findTeacherByIdFirstNameAndLastName(id);
        model.addFlashAttribute("message",String.format("Deleted %s course assigned to %s ",name,teacherFirstAndLastName));}
        else{
            model.addFlashAttribute("message", String.format("Couldn't delete %s to %s because course don't exist",
                    name, teacherService.findTeacherByIdFirstNameAndLastName(id)));
        }
        return "redirect:/teachers/teacherlist";
    }


    //Add Course to Teacher
    @PostMapping("{id}/savecoursestoteacher")
    public String saveCourseToTeacher(@RequestParam("courses-choice") String name, @PathVariable("id") int id, RedirectAttributes model) {

        // check course is on the list
        boolean isCourseNameValid = courseService.findIfCourseExist(name);
        String teacherFirstAndLastName=teacherService.findTeacherByIdFirstNameAndLastName(id);
        if (isCourseNameValid) {
            try {
                teacherService.addCourse(id, courseService.findCourseByName(name));
                model.addFlashAttribute("message", String.format("Added %s to %s",
                        name, teacherFirstAndLastName));
                log.info(String.format
                        ("Added %s to the teacher %S",
                                name, teacherFirstAndLastName));
            } catch (RuntimeException ex) {
                model.addFlashAttribute("message", String.format("Couldn't persist %s to %s", name, teacherFirstAndLastName));
                log.error(String.format("Couldn't persist %s to %s", name, teacherFirstAndLastName));
                ex.printStackTrace();
            }
        } else {
            model.addFlashAttribute("message", String.format("Couldn't persist %s to %s because course don't exist",
                    name, teacherService.findTeacherByIdFirstNameAndLastName(id)));
            log.warn(String.format("Couldn't persist %s to %s because course doesn't exist", name, id));
        }
        log.info("courses-choice:" + name);
        log.info("isCourseNameValid: " + isCourseNameValid);

        return "redirect:/teachers/teacherlist";
    }
}
