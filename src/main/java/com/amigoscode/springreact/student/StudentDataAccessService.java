package com.amigoscode.springreact.student;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentDataAccessService {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public List<Student> selectAllStudents() {
        String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, new StudentRowMapper());
    }

    public void insertStudent(UUID newStudentId, Student student) {
        String sql = "INSERT INTO student (student_id, first_name, last_name, email, gender) " +
                "VALUES (?,?,?,?,?::gender)";
        jdbcTemplate.update(sql,
                newStudentId, student.getFirstName(), student.getLastName(),
                student.getEmail(), student.getGender().name().toUpperCase()
        );
    }

    public Boolean isEmailTaken(Student student) {
        String sql = "SELECT EXISTS (SELECT 1 FROM student WHERE email = ? )";
        return jdbcTemplate.queryForObject(sql, Boolean.class, student.getEmail());
    }

    public List<StudentCourse> selectAllStudentCoursesFromStudentById(UUID studentId) {
        String sql = "SELECT student.student_id, course.course_id, course.name," +
                "course.description, course.departament, course.teacher_name," +
                "student_course.start_date, student_course.end_date, student_course.grade " +
                "FROM student " +
                "JOIN student_course USING(student_id) " +
                "JOIN course USING(course_id) WHERE student_id = ?";
        return jdbcTemplate.query(sql, new StudentCourseRowMapper(), studentId);
    }
}
