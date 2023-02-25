package org.anjanadevijaulikrishnamoorthy.myapp.contorllers;

import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.TeacherRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dto.TeacherDTO;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.anjanadevijaulikrishnamoorthy.myapp.services.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "teachers")
public class TeacherController {

   final TeacherRepoI teacherRepoI;
   final TeacherService teacherService;


    public TeacherController(TeacherRepoI teacherRepoI, TeacherService teacherService) {
        this.teacherRepoI = teacherRepoI;
        this.teacherService = teacherService;
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




}
