package org.anjanadevijaulikrishnamoorthy.myapp.contorllers;

import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.CourseRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.ScoreRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.StudentRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Score;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "scores")
public class ScoreController {
     ScoreRepoI scoreRepoI;
     CourseRepoI courseRepoI;
     StudentRepoI studentRepoI;

    public ScoreController(ScoreRepoI scoreRepoI, CourseRepoI courseRepoI, StudentRepoI studentRepoI) {
        this.scoreRepoI = scoreRepoI;
        this.courseRepoI = courseRepoI;
        this.studentRepoI = studentRepoI;
    }
   @GetMapping("/selectStudentToAddCourses")
   public ModelAndView selectStudent(@RequestParam int id){
       ModelAndView mv = new ModelAndView("studentcourse");
       Student s = studentRepoI.findById(id).get();
       List<Course> c = courseRepoI.findAll();
       mv.addObject("student",s);
       mv.addObject("courses",c);

       return mv;
   }
    @PostMapping("/addCoursesToStudent")
    public String addCoursesToStudent(@RequestParam(name="id") int sid, @RequestParam(name="cid") String[] cid, Model model){
        Student s = studentRepoI.findById(sid).get();
        List<Course> courseAssignedToStudent = new ArrayList<>();
        Score mark=new Score();
            for(String ids :cid) {
                log.warn(ids);
                Course c = courseRepoI.findByCourseName(ids).get();
                courseAssignedToStudent.add(c);
                 scoreRepoI.save(new Score(s, c));
            }
            model.addAttribute("student",s);
            model.addAttribute("courses",courseAssignedToStudent);
        return "scoreform";
    }
   @PostMapping("/addStudentCourseScore")
    public String addStudentCourseScore(@RequestParam(name="mark") double mark,@RequestParam(name="id") int sid){
        Student s= studentRepoI.findById(sid).get();
        Course c= scoreRepoI.findByStudent(s);
        scoreRepoI.save(new Score(s,c,mark));
        return "scoreform";

   }




}
