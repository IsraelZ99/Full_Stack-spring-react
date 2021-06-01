package com.amigoscode.springreact.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class StudentCourse {

    private UUID studentId;
    private UUID courseId;
    private String name;
    private String description;
    private String department;
    private String teacherName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer grade;

}
