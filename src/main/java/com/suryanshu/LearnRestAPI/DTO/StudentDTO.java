package com.suryanshu.LearnRestAPI.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    @NotBlank
    @Size(min = 2 ,max = 30,message = "name should be of 2 to 30 char and should not be blank")
    private String name;
    @Email
    @NotBlank(message = "put your email here and it should be vailid")
    private String email;
    @Size(max = 500, message = "courses should not exceed 500 characters")
    private String courses;

}
