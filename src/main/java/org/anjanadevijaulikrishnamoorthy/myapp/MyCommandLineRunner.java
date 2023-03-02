package org.anjanadevijaulikrishnamoorthy.myapp;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.CourseRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.ScoreRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.StudentRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.TeacherRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Score;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.anjanadevijaulikrishnamoorthy.myapp.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import java.util.List;
import java.util.NoSuchElementException;


@Component
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyCommandLineRunner implements CommandLineRunner {
    final TeacherService teacherService;
    final StudentRepoI studentRepoI;
    final CourseRepoI courseRepoI;
    final TeacherRepoI teacherRepoI;
    final ScoreRepoI scoreRepoI;


    @Autowired
    public MyCommandLineRunner(StudentRepoI studentRepoI,
                               CourseRepoI courseRepoI,
                               TeacherRepoI teacherRepoI,
                               ScoreRepoI scoreRepoI,
                               TeacherService teacherService
                              ) {
        this.studentRepoI = studentRepoI;
        this.courseRepoI = courseRepoI;
        this.teacherRepoI = teacherRepoI;
        this.scoreRepoI=scoreRepoI;
        this.teacherService=teacherService;

    }

    @PostConstruct
    void created() {
        log.warn("=============== My CommandLineRunner Got Created ===============");
    }
    @Override
    public void run(String... args) throws Exception {
    Student s1 = (new Student("Ganesh", "Venkat",
            LocalDate.of(2015,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), 'M', 1));
    Student s2 = (new Student("Sai", "Ram",
            LocalDate.of(2015, 5, 15), 'M', 1));
    Student s3 = (new Student("Kelly", "Duncan",
            LocalDate.of(2015, 4, 20), 'F', 1));
    Student s4 = (new Student("Ashley", "Manton",
            LocalDate.of(2015, 6, 27), 'F', 1));
    Student s5 = (new Student("Mathew", "Mark",
            LocalDate.of(2015, 2, 22), 'M', 1));
    Student s6 = (new Student("Aarthi", "Jeeva",
            LocalDate.of(2015, 1, 11), 'F', 1));
    Student s7 = (new Student("Karhika", "Satis",
            LocalDate.of(2015, 2, 6), 'F', 1));
    Student s8 = (new Student("Vignesh", "Vela",
            LocalDate.of(2014, 12, 5), 'M', 2));
    Student s9 = (new Student("Vijaya", "Jeyaram",
            LocalDate.of(2014, 2, 11), 'F', 2));
    Student s10 = (new Student("Preetha", "Jai",
            LocalDate.of(2014,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), 'F', 2));
    Student s11 = (new Student("Gomathi", "Siva",
            LocalDate.of(2014, 7, 19), 'F', 2));
    Student s12 = (new Student("Sivaram", "Ganeshan",
            LocalDate.of(2014, 9, 6), 'M', 2));
    Student s13 = (new Student("Sally", "Bray",
            LocalDate.of(2014, 3, 14), 'F', 2));
    Student s14 = (new Student("Laura", "King",
            LocalDate.of(2014, 1, 18), 'F', 2));
    Student s15 = (new Student("Kevin", "Ven",
            LocalDate.of(2013, 4, 15), 'M', 3));
    Student s16 = (new Student("Balaji", "Sundar",
            LocalDate.of(2013, 2, 5), 'M', 3));
    Student s17 = (new Student("Gandhi", "Mohan",
            LocalDate.of(2013,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), 'M', 3));
    Student s18 = (new Student("Harry", "Potter",
            LocalDate.of(2013, 6, 8), 'M', 3));
    Student s19 = (new Student("Nick", "Sammy",
            LocalDate.of(2013, 8, 12), 'M', 3));
    Student s20 = (new Student("Olivia", "Vest",
            LocalDate.of(2013, 9, 14), 'F', 3));
    Student s21 = (new Student("Angela", "Brown",
            LocalDate.of(2013, 10, 5), 'F', 3));
    Student s22 = (new Student("Ella", "Campbell",
            LocalDate.of(2012, 4, 5), 'F', 4));
    Student s23 = (new Student("Brian", "Baker",
            LocalDate.of(2012, 4, 5), 'M', 4));
    Student s24 = (new Student("Austin", "Butler",
            LocalDate.of(2012,LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth()), 'M', 4));
    Student s25 = (new Student("Charles", "Davies",
            LocalDate.of(2012, 4, 25), 'M', 4));
    Student s26 = (new Student("Alex", "Clark",
            LocalDate.of(2012, 1, 15), 'M', 4));
    Student s27 = (new Student("Erica", "Black",
            LocalDate.of(2012, 2, 7), 'F', 4));
    Student s28 = (new Student("Eric", "Baker",
            LocalDate.of(2012, 3, 2), 'M', 4));
    Student s29 = (new Student("Amanda", "Coleman",
            LocalDate.of(2012, 5, 2), 'F', 4));
    Student s30 = (new Student("Ella", "Bond",
            LocalDate.of(2012, 9, 1), 'F', 4));
   studentRepoI.saveAll(List.of(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,
           s12,s13,s14,s15,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28,
           s29,s30));

   Course math = new Course("Mathemetics");
   Course science = new Course("Science");
   Course language = new Course("English");
   Course social = new Course("Social");

   courseRepoI.saveAll(List.of(math,science,social,language));




//        try {
//            Thread.sleep(2000);
//            Student s = studentRepoI.findById(1).get();
//            Course c= courseRepoI.findById(1).get();
//            //Get database connection, delete unused data from DB
//            Score s1m = new Score(s,c,70);
//           scoreRepoI.save(s1m);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


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



        double doub_value = 70.00;
        scoreRepoI.save(new Score(s1,science,doub_value));

        try {
            teacherService.addCourse(1,math);
            teacherService.addCourse(2,science);
            teacherService.addCourse(3,language);
            teacherService.addCourse(4,social);
            teacherService.addCourse(1,science);


        } catch (NoSuchElementException ex){
            log.error("Couldn't add course to student!");
            ex.printStackTrace();
        } catch (RuntimeException e){
            log.error("Couldn't add courses!");
            e.printStackTrace();
        }







    }


}
