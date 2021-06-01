package com.amigoscode.springreact.student;

import com.amigoscode.springreact.exception.ApiRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/students")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        /*throw new ApiRequestException("Oops cannot get all students with custom exception");*/
        return studentService.getAllStudents();
    }

    @PostMapping
    public void addNewStudent(@Valid @RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @GetMapping(path = "{studentId}/courses")
    public List<StudentCourse> getAllCoursesForStudent(@PathVariable UUID studentId){
        return studentService.getAllCoursesForStudentById(studentId);
    }

    /*
        INSERT INTO student_course (student_id, course_id, start_date, end_date) VALUES
            ('40f6d68a-90e8-47e3-bdd9-d376ef5c1627', 'e0aadec0-ef50-4e02-8a7a-1cc2b7258bde',
            NOW()::DATE, (NOW() + INTERVAL '1 YEAR')::DATE);

        ***** QUERY CUANDO AMBAS COLUMNAS SE LLAMAN DE LA MISMA FORMA Y TIENEN LLAVE FORANEA ***
        SELECT * FROM student JOIN student_course USING (student_id)
            JOIN course USING (course_id);

            -> Esto equivale a
        SELECT * FROM student
            JOIN student_course ON student.student_id = student_course.student_id
            JOIN course ON student_course.course_id = course.course_id

    */
}
