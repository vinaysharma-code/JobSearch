package com.jobSearch.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Password is required")
    @Size(min = 6 ,max =50 ,message = "Enter a secure Password")
    private String password ;


    @NotBlank(message = "Email is Required")
    @Size(max = 100 ,message = "Enter a valid email address")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Invalid email format"
    )
    private String email ;

}
