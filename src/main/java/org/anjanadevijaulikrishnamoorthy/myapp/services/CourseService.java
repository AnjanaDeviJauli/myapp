package org.anjanadevijaulikrishnamoorthy.myapp.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.CourseRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.dto.CourseDTO;
import org.anjanadevijaulikrishnamoorthy.myapp.dto.TeacherDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackOn = {DataAccessException.class})
public class CourseService {
    CourseRepoI courseRepoI;
    TeacherService teacherService;

    public CourseService(CourseRepoI courseRepoI, TeacherService teacherService) {
        this.courseRepoI = courseRepoI;

        this.teacherService = teacherService;
    }


    public List<CourseDTO> getCourseEssInfo(){

        return
                courseRepoI.findAll()
                .stream()
                .map((oneCourse)-> {
                    return new CourseDTO(oneCourse.getId(),oneCourse.getCourseName(),teacherService.getTeacherDetailsForCourse(oneCourse));
                })
                .collect(Collectors.toList());
//
//
    }
}
