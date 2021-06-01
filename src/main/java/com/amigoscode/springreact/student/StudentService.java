package com.amigoscode.springreact.student;

import com.amigoscode.springreact.EmailValidator;
import com.amigoscode.springreact.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private final StudentDataAccessService studentDataAccessService;

    @Autowired
    private final EmailValidator emailValidator;

    public List<Student> getAllStudents() {
        return studentDataAccessService.selectAllStudents();
    }

    public void addNewStudent(Student student) {
        // TODO: Validate email.
        if (!emailValidator.test(student.getEmail())) {
            throw new ApiRequestException(student.getEmail().concat(" is not valid"));
        }
        // TODO: Verify that email is not taken.
        if(studentDataAccessService.isEmailTaken(student)){
            throw new ApiRequestException(student.getEmail().concat(" is already taken"));
        }
        studentDataAccessService.insertStudent(UUID.randomUUID(), student);
    }

    public void addNewStudent(UUID studentId, Student student) {
        UUID newStudentId = Optional.ofNullable(studentId).orElse(UUID.randomUUID());
        studentDataAccessService.insertStudent(newStudentId, student);
    }

    public List<StudentCourse> getAllCoursesForStudentById(UUID studentId){
        return studentDataAccessService.selectAllStudentCoursesFromStudentById(studentId);
    }

}
