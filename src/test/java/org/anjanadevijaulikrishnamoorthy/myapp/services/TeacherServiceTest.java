package org.anjanadevijaulikrishnamoorthy.myapp.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class TeacherServiceTest {

    TeacherService teacherService;

    @Autowired
    public TeacherServiceTest(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    String firstnameAndLastname = "Ashley Minton";
    @Test
    void findTeacherByIdFirstNameAndLastName() {
        assertThat(teacherService.findTeacherByIdFirstNameAndLastName(1)).isEqualTo(firstnameAndLastname);

    }
}