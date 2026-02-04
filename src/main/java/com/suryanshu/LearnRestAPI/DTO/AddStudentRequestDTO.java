package com.suryanshu.LearnRestAPI.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddStudentRequestDTO {
    @NotBlank
    @Size(min = 2 ,max = 30,message = "name should be of 2 to 30 char and should not be blank")
    private String name;
    @Email
    @NotBlank(message = "put your email here and it should be vailid")
    private String email;
}
