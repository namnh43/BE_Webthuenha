package com.example.springboot.dao.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    private String username;
    @Size(min = 6, max = 32, message = "Password length must be between 6 and 34 characters")

    private String password;
    private String phoneNumber;
    private String email;
}
