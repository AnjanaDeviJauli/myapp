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
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView selectStudent(@RequestParam int id,Model model) throws Exception {
        ModelAndView mv = new ModelAndView("studentcourse");
        Student s = studentRepoI.findById(id).get();
        List<Course> c = courseRepoI.findAll();
        c.removeAll(scoreRepoI.findCourseByStudent(s));
        if(c==null){
            model.addAttribute("message","No more course to add");
            throw new Exception();
        }
        mv.addObject("student", s);
        mv.addObject("courses", c);

        return mv;
    }

    @PostMapping("/addCoursesToStudent")
    public String addCoursesToStudent(@RequestParam(name = "id") int sid, @RequestParam(name = "cid",required = false) String[] cid, Model model) {
        Student s = studentRepoI.findById(sid).get();
        List<Course> courseAssignedToStudent = new ArrayList<>();
        List<Score> studentScore = new ArrayList<>();
        double mark = 0;
        for (String ids : cid) {

            log.warn(ids);
            Course c = courseRepoI.findByCourseName(ids).get();


            scoreRepoI.save(new Score(s, c, mark));

        }
        courseAssignedToStudent=scoreRepoI.findCourseByStudent(s);
         studentScore=scoreRepoI.findScoreByStudent(s);
        model.addAttribute("student", s);
        model.addAttribute("courses", courseAssignedToStudent);
        model.addAttribute("scores",studentScore);
//        return "redirect:/students/findStudentbyParam?="+sid;
        return "redirect:/students/list";
    }

@GetMapping("/studentScore")
    public String studentscore(@RequestParam int id,Model model){
    Student s = studentRepoI.findById(id).get();
        List<Score> studentScore=scoreRepoI.findScoreByStudent(s);
        model.addAttribute("scores",studentScore);
        return "scorelist";
}
@PostMapping("/saveScoretoStudenCourse")
    public String addStudentCourseScore(@RequestParam int id, @RequestParam (name="mark") double mark, Model model){
        Score s = scoreRepoI.findById(id).get();
        log.warn(s.toString());
        s.setMark(mark);
        scoreRepoI.save(s);
    List<Score> studentScore=scoreRepoI.findScoreByStudent(s.getStudent());
    model.addAttribute("scores",studentScore);
    return "scorelist";
}



}
