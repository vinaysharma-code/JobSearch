package com.jobSearch.dto.response;

import com.jobSearch.enums.Role;
import lombok.Data;

@Data
public class RegisterResponse {
    private String userName ;
    private String email ;
    private Role role ;
    private String token ;
}
