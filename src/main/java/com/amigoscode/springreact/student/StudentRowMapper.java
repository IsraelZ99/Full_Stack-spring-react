package com.amigoscode.springreact.student;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class StudentRowMapper implements RowMapper<Student> {

    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        String studentIdStr = rs.getString("student_id");
        UUID studentId = UUID.fromString(studentIdStr);
        student.setStudentId(studentId);
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setEmail(rs.getString("email"));
        String genderStr = rs.getString("gender").toUpperCase();
        Student.Gender gender = Student.Gender.valueOf(genderStr);
        student.setGender(gender);
        return student;
    }

}
