package org.anjanadevijaulikrishnamoorthy.myapp.services;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.anjanadevijaulikrishnamoorthy.myapp.dao.StudentRepoI;
import org.anjanadevijaulikrishnamoorthy.myapp.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(rollbackOn = {DataAccessException.class})
public class StudentService {
    StudentRepoI studentRepoI;
    @Autowired
    public StudentService(StudentRepoI studentRepoI) {
        this.studentRepoI = studentRepoI;
    }

    public Student findStudentById(int id){
        return studentRepoI.findById(id).get();
    }


}
