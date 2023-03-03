package org.anjanadevijaulikrishnamoorthy.myapp.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.CourseRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.TeacherRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dto.TeacherDTO;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Course;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Teachers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service @Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackOn = {DataAccessException.class})
public class TeacherService {
    CourseRepoI courseRepoI;
     TeacherRepoI teacherRepoI;



    @Autowired
    public TeacherService(CourseRepoI courseRepoI,
                          TeacherRepoI teacherRepoI
    ) {
        this.courseRepoI = courseRepoI;
        this.teacherRepoI = teacherRepoI;



    }

    @Transactional(rollbackOn = {NoSuchElementException.class})
    public void deletCourse(Integer id,String name) throws NoSuchElementException{
        Teachers t = teacherRepoI.findById(id).orElseThrow();
        t.deleteCourses(courseRepoI.findByCourseName(name).get());
    }
    //adding course to teacher
    @Transactional(rollbackOn = {NoSuchElementException.class})
    public void addCourse(Integer id, Course c) throws NoSuchElementException{

        Teachers t = teacherRepoI.findById(id).orElseThrow();
        c = courseRepoI.save(c);
        t.addCourses(c);
        teacherRepoI.save(t);
    }



    public List<TeacherDTO> getTeachersEssInfo(){

        return teacherRepoI
                .findAll()
                .stream()
                .map((oneTeacher)-> {
                    return new TeacherDTO(oneTeacher.getId(),oneTeacher.getFirstNameT(), oneTeacher.getLastNameT(),
                    oneTeacher.getEmail(),oneTeacher.getUsername(),oneTeacher.getCourses());
                })
                .collect(Collectors.toList());
//
//
    }
    public List<TeacherDTO> getTeacherDetailsForCourse(Course c){
        return courseRepoI
                .findTeacherAssignedToCourse(c.getId())
                .stream()
                .map((oneTeacher)-> {
                    return new TeacherDTO(oneTeacher.getId(),oneTeacher.getFirstNameT(), oneTeacher.getLastNameT(),
                            oneTeacher.getEmail(),oneTeacher.getUsername(),oneTeacher.getCourses());
                })
                .collect(Collectors.toList());
    }

    public String findTeacherByIdFirstNameAndLastName(int id){
      return   teacherRepoI.findById(id).get().getFirstNameT()+" "+teacherRepoI.findById(id).get().getLastNameT();
    }
}
