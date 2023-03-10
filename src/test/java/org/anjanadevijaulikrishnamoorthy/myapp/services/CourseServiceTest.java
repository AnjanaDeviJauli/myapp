package org.anjanadevijaulikrishnamoorthy.myapp.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class CourseServiceTest {
    CourseService courseService;

    @Autowired
    public CourseServiceTest(CourseService courseService) {
        this.courseService = courseService;
    }

    @Test
    void findIfCourseExist() {
        assertThat(courseService.findIfCourseExist("Science")).isTrue();
    }
}