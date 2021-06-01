package com.amigoscode.springreact.student;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class StudentCourseRowMapper implements RowMapper<StudentCourse> {

    public StudentCourse mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentCourse course = new StudentCourse();
        String courseIdStr = rs.getString("course_id");
        UUID courseId = UUID.fromString(courseIdStr);
        course.setCourseId(courseId);
        String studentIdStr = rs.getString("student_id");
        UUID studentId = UUID.fromString(studentIdStr);
        course.setStudentId(studentId);
        course.setName(rs.getString("name"));
        course.setDescription(rs.getString("description"));
        course.setTeacherName(rs.getString("teacher_name"));
        course.setStartDate(rs.getDate("start_date").toLocalDate());
        course.setEndDate(rs.getDate("end_date").toLocalDate());
        int grade = Optional.of(rs.getInt("grade")).orElse(null);
        course.setGrade(grade == 0 ? null : grade);
        return course;
    }

}
