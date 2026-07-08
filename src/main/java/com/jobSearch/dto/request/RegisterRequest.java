package com.jobSearch.dto.request;

import com.jobSearch.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class RegisterRequest {
    @Size(min = 3, max = 50 , message = "Enter  UserName")
    private String userName ;

    @NotBlank(message = "Enter Email")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Invalid email format"
    )
    private String email ;
    @NotBlank
    @Size(min = 6 ,max =50 ,message = "Enter a secure Password")
    private String password ;



}
