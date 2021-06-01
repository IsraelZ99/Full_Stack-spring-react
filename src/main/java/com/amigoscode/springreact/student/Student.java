package com.amigoscode.springreact.student;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Student {

    private UUID studentId;

    @NotBlank(message = "The first Name must not be blank")
    private String firstName;

    @NotBlank(message = "The last Name must not be blank")
    private String lastName;

    @NotBlank(message = "The email must not be blank")
    private String email;

    @NotNull(message = "Enter a valid gender")
    private Gender gender;

    public enum Gender {
        MALE, FEMALE
    }

}
