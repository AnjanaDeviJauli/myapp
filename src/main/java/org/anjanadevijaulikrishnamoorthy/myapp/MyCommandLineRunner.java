package org.anjanadevijaulikrishnamoorthy.myapp;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.CourseRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.StudentCourseScoreRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.StudentRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.TeacherRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.anjanadevijaulikrishnamoorthy.myapp.models.StudentCourseScore;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import java.util.List;


@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyCommandLineRunner implements CommandLineRunner {
    StudentRepoI studentRepoI;
    CourseRepoI courseRepoI;
    TeacherRepoI teacherRepoI;
    StudentCourseScoreRepoI studentCourseScoreRepoI;

    @Autowired
    public MyCommandLineRunner(StudentRepoI studentRepoI,
                               CourseRepoI courseRepoI,
                               TeacherRepoI teacherRepoI,
                               StudentCourseScoreRepoI studentCourseScoreRepoI) {
        this.studentRepoI = studentRepoI;
        this.courseRepoI = courseRepoI;
        this.teacherRepoI = teacherRepoI;
        this.studentCourseScoreRepoI = studentCourseScoreRepoI;
    }

    @PostConstruct
    void created() {
        log.warn("=============== My CommandLineRunner Got Created ===============");
    }
    @Override
    public void run(String... args) throws Exception {
    Student s1 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2015, 4, 5), 'M', 1));
    Student s2 = (new Student("Sai", "Ram",
            LocalDate.of(2015, 5, 15), 'M', 1));
    Student s3 = (new Student("Kelly", "Duncan",
            LocalDate.of(2015, 4, 20), 'M', 1));
    Student s4 = (new Student("Ashley", "Minton",
            LocalDate.of(2015, 6, 27), 'M', 1));
    Student s5 = (new Student("Mathew", "Mark",
            LocalDate.of(2015, 2, 22), 'M', 1));
    Student s6 = (new Student("Aarthi", "Jeeva",
            LocalDate.of(2015, 1, 11), 'M', 1));
    Student s7 = (new Student("Karhika", "Satis",
            LocalDate.of(2015, 2, 6), 'M', 1));
    Student s8 = (new Student("Vignesh", "Vela",
            LocalDate.of(2014, 12, 5), 'M', 2));
    Student s9 = (new Student("Vijaya", "Jeyaram",
            LocalDate.of(2014, 2, 11), 'M', 2));
    Student s10 = (new Student("Preetha", "Jai",
            LocalDate.of(2014, 4, 24), 'M', 2));
    Student s11 = (new Student("Gomathi", "Siva",
            LocalDate.of(2014, 7, 19), 'M', 2));
    Student s12 = (new Student("Sivaram", "Ganeshan",
            LocalDate.of(2014, 9, 6), 'M', 2));
    Student s13 = (new Student("Sally", "Bray",
            LocalDate.of(2014, 3, 14), 'M', 2));
    Student s14 = (new Student("Laura", "King",
            LocalDate.of(2014, 1, 18), 'M', 2));
    Student s15 = (new Student("Kevin", "Venkat",
            LocalDate.of(2013, 4, 15), 'M', 3));
    Student s16 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2013, 2, 5), 'M', 3));
    Student s17 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2013, 1, 6), 'M', 3));
    Student s18 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2013, 6, 8), 'M', 3));
    Student s19 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2013, 8, 12), 'M', 3));
    Student s20 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2013, 9, 14), 'M', 3));
    Student s21 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2013, 10, 5), 'M', 3));
    Student s22 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2012, 4, 5), 'M', 4));
    Student s23 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2012, 4, 5), 'M', 4));
    Student s24 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2012, 4, 5), 'M', 4));
    Student s25 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2012, 4, 25), 'M', 4));
    Student s26 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2012, 1, 15), 'M', 4));
    Student s27 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2012, 2, 7), 'M', 4));
    Student s28 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2012, 3, 2), 'M', 4));
    Student s29 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2012, 5, 2), 'M', 4));
    Student s30 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2012, 9, 1), 'M', 4));
   studentRepoI.saveAll(List.of(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,
           s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,
           s29,s30));

   Course math = new Course("Mathemetics");
   Course science = new Course("Science");
   Course language = new Course("English");
   Course social = new Course("Social");

   courseRepoI.saveAll(List.of(math,science,social,language));

   Teachers t1 = new Teachers("Ashley","Minton",
           "ashley@gmail.com","ashleyminton","1234");
   Teachers t2 = new Teachers("Elizabeth","Watson",
                "elizabeth@gmail.com","elizabethwatson","1234");
   Teachers t3 = new Teachers("Jamey","Duncan",
                "jamey@gmail.com","jameyduncan","1234");
   Teachers t4 = new Teachers("Anjana","Devi",
                "anjana@gmail.com","anjanadevi","1234");
   Teachers t5 = new Teachers("Vathsala","Suresh",
                "vathsala@gmail.com","vathsalasuresh","1234");

   teacherRepoI.saveAll(List.of(t1,t2,t3,t4,t5));

   StudentCourseScore sc1m = new StudentCourseScore(s1,math,99);
   StudentCourseScore sc1s = new StudentCourseScore(s1,science,99);
   StudentCourseScore sc1o = new StudentCourseScore(s1,social,99);
   StudentCourseScore sc1l = new StudentCourseScore(s1,language,99);
   StudentCourseScore sc2m = new StudentCourseScore(s2,math,99);
   StudentCourseScore sc2s = new StudentCourseScore(s2,science,99);
   StudentCourseScore sc2o = new StudentCourseScore(s2,social,99);
   StudentCourseScore sc2l = new StudentCourseScore(s2,language,99);
   StudentCourseScore sc3m = new StudentCourseScore(s3,math,99);
   StudentCourseScore sc3s = new StudentCourseScore(s3,science,99);
   StudentCourseScore sc3o = new StudentCourseScore(s3,social,99);
   StudentCourseScore sc3l = new StudentCourseScore(s3,language,99);
   StudentCourseScore sc4m = new StudentCourseScore(s4,math,99);
   StudentCourseScore sc4s = new StudentCourseScore(s4,science,99);
   StudentCourseScore sc4o = new StudentCourseScore(s4,social,99);
   StudentCourseScore sc4l = new StudentCourseScore(s4,language,99);
   StudentCourseScore sc5m = new StudentCourseScore(s5,math,99);
   StudentCourseScore sc5s = new StudentCourseScore(s5,science,99);
   StudentCourseScore sc5o = new StudentCourseScore(s5,social,99);
   StudentCourseScore sc5l = new StudentCourseScore(s5,language,99);







    }


}
