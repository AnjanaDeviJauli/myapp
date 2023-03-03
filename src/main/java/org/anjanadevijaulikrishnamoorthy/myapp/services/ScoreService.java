package org.anjanadevijaulikrishnamoorthy.myapp.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.CourseRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.ScoreRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.StudentRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Score;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackOn = {DataAccessException.class})
public class ScoreService {

    ScoreRepoI scoreRepoI;
    CourseRepoI courseRepoI;
    StudentRepoI studentRepoI;


    public ScoreService(ScoreRepoI scoreRepoI, CourseRepoI courseRepoI, StudentRepoI studentRepoI) {
        this.scoreRepoI = scoreRepoI;
        this.courseRepoI = courseRepoI;
        this.studentRepoI = studentRepoI;
    }

    public String findGrade(double average) {
        String grade = null;
        switch ((int) (average / 10)) {
            // for >= 90
            case 10:
            case 9:
                grade = "A";
                break;
            // for >= 80 and <90
            case 8:
                grade = "B";
                break;
            // for >= 70 and <80
            case 7:
                grade = "C";
                break;
            // for >= 60 and <70
            case 6:
                grade = "D";
                break;
            // for >= 50 and <60
            case 5:
                grade = "E";
                break;
            // for < 50
            default:
                grade = "F";
                break;
        }
        return grade;
    }

    public void assignCourseToStudent(Student s, String[] cid) {
        double mark = 0;
        try{
        for (String ids : cid) {
            log.warn(ids);
            Course c = courseRepoI.findByCourseName(ids).get();
            scoreRepoI.save(new Score(s, c, mark));
        }}catch (RuntimeException ex){
            ex.printStackTrace();

        }
    }


    public List<Course> findCourseByStudent(Student s){
        return   scoreRepoI.findCourseByStudent(s);
    }

    public List<Score> findScoreByStudent(Student s){
        return  scoreRepoI.findScoreByStudent(s);
    }


    public void saveScore(Score s){
        scoreRepoI.save(s);
    }

    public double findStudentAverageScore(Student s){
      List<Score> score =  scoreRepoI.findScoreByStudent(s);
      int count=0;
      for(Score mark:score){
          if(mark.getMark()>0){
              count++;
          }

      }

        return scoreRepoI.findStudentScoreSum(s)/count;

   //   return   scoreRepoI.findStudentAverageScore(s);
    }

    public Score findByScoreId(int sid){
        return scoreRepoI.findById(sid).get();
    }

}
