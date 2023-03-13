package org.anjanadevijaulikrishnamoorthy.myapp.contorllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.ScoreRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.TeacherRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Score;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.anjanadevijaulikrishnamoorthy.myapp.services.CourseService;
import org.anjanadevijaulikrishnamoorthy.myapp.services.ScoreService;
import org.anjanadevijaulikrishnamoorthy.myapp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "scores")
public class ScoreController {
    private final TeacherRepoI teacherRepoI;
    private final ScoreRepoI scoreRepoI;

    ScoreService scoreService;

    CourseService courseService;

    StudentService studentService;
    List<Student> highScoreStudent = new ArrayList<>();
    List<Student> lowScoreStudent = new ArrayList<>();
    List<String> ho = new ArrayList<>();
    List<String> lo = new ArrayList<>();

    Course c = new Course();
    @Autowired
    public ScoreController(ScoreService scoreService, CourseService courseService, StudentService studentService,
                           ScoreRepoI scoreRepoI,
                           TeacherRepoI teacherRepoI) {
        this.scoreService = scoreService;
        this.courseService = courseService;
        this.studentService = studentService;
        this.scoreRepoI = scoreRepoI;
        this.teacherRepoI = teacherRepoI;
    }
    static final String MESSAGE="message";
    static final String SCORES = "scores";
    static final String SCORELIST = "scorelist";

    //Show registration form to assign course for each student
    @GetMapping("/selectStudentToAddCourses")
    public ModelAndView selectStudent(@RequestParam int id, Model model) throws Exception {

        //Creating ModelAndView to add student and course object
        ModelAndView mv = new ModelAndView("studentcourse");

        //Finding Student using student id got as hidden input
        Student s = studentService.findStudentById(id);

        //Finding all courses available at school
        List<Course> c = courseService.findAllCourses();

        //Removing courses already assigned to student, so that admin cannot add duplicate course to the same student
        c.removeAll(scoreService.findCourseByStudent(s));

        //Checking null if all course is already assigned to student
        if (c == null) {
            model.addAttribute(MESSAGE, "No more course to add");
            throw new Exception();
        }

        //adding student and course object to the ModelAndView
        mv.addObject("student", s);
        mv.addObject("courses", c);

        return mv;
    }


    //save course to each student after submitting studentcourse form
    @PostMapping("/addCoursesToStudent")
    public String addCoursesToStudent(@RequestParam(name = "id") int sid, @RequestParam(name = "cid", required = false) String[] cid, Model model) {
        //Find the student using hidden input id
        Student s = studentService.findStudentById(sid);

        //Creating Course and Score object to assign Course and enter Score
        List<Course> courseAssignedToStudent = new ArrayList<>();
        List<Score> studentScore = new ArrayList<>();


        //Assigning all the course from checklist to the student and updating score object
        scoreService.assignCourseToStudent(s, cid);

        //Finding course assigned to student and mark for each course
        courseAssignedToStudent = scoreService.findCourseByStudent(s);
        studentScore = scoreService.findScoreByStudent(s);
        model.addAttribute("student", s);
        model.addAttribute("courses", courseAssignedToStudent);
        model.addAttribute(SCORES, studentScore);

        //  return "redirect:/students/findStudentbyParam?="+sid;
        return SCORELIST;
    }






    //display student score when Add Score button is clicked for each student
    @GetMapping("/studentScore")
    public String studentscore(@RequestParam int id, Model model) {
        Student s = studentService.findStudentById(id);
        List<Score> studentScore = scoreService.findScoreByStudent(s);
        model.addAttribute(SCORES, studentScore);
        return SCORELIST;
    }

    //To enter/update mark for each Course assigned to a Student
    @PostMapping("/saveScoretoStudenCourse")
    public String addStudentCourseScore(@Valid @RequestParam int id, @RequestParam(name = "mark") double mark, Model model, Principal principal) throws Exception {
        //Find the score corresponding to each course using score id
        Score s = scoreService.findByScoreId(id);
       c=s.getCourse();
        String email = principal.getName();

        List<Course> courses = teacherRepoI.findCourseByAuthEmail(email);


        log.warn(s.toString());
        if(mark<=100 && mark>=0 && courses.contains(c) || email.equalsIgnoreCase("anjana@gmail.com")) {


            //set mark for that score corresponding to the course since each score object has its
            //student and course object
            s.setMark(mark);

            //save the updated score object
            scoreService.saveScore(s);

        }else throw new Exception();


        //The following attribute is used to display score details of the particular student
        List<Score> studentScore = scoreService.findScoreByStudent(s.getStudent());
        model.addAttribute(SCORES, studentScore);

        //Finding the average of the mark for that student
       // double average = scoreService.findStudentAverageScore(s.getStudent());
        model.addAttribute("average", s.getMark());

        //Display exam grade according to the average
        String examgrade = scoreService.findGrade(s.getMark());

        model.addAttribute("examgrade",examgrade);



        if(examgrade.equalsIgnoreCase("A") && !highScoreStudent.contains(s.getStudent())){
            highScoreStudent.add(s.getStudent());


        }else if(examgrade.equalsIgnoreCase("f")||(examgrade.equalsIgnoreCase("e"))
        || (examgrade.equalsIgnoreCase("d"))||(examgrade.equalsIgnoreCase("c")) &&
                !lowScoreStudent.contains(s.getStudent())){
            lowScoreStudent.add(s.getStudent());


        }

        //display the score details of the student with input form to enter score/mark.
        return SCORELIST;
    }

    @GetMapping("/deleteStudentCourse")
    public String deleteCourseScore(@RequestParam int id,Model model) {
        //Find the score corresponding to each course using score id
        int ids = scoreRepoI.findStudentByScore(id).getId();
        Score s = scoreRepoI.findById(id).get();
        scoreRepoI.delete(s);

        return "redirect:/scores/studentScore?id="+ids;
    }



    @GetMapping("/highScoreStudents")
    public String findHighScoreStudent(Model model){


        model.addAttribute("allstu",highScoreStudent);
        model.addAttribute(MESSAGE,"Students in Extension/Advanced class");
        return "birthdaystoday";
    }
    @GetMapping("/lowScoreStudents")
    public String findlowScoreStudent(Model model){


        model.addAttribute("allstu",lowScoreStudent);
        model.addAttribute(MESSAGE,"Students attending mandatory tutoring");
        return "birthdaystoday";
    }


}
