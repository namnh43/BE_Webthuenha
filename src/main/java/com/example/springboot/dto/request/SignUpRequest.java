package com.example.springboot.dto.request;

import jakarta.validation.constraints.Pattern;
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
    @Size(min = 6, max = 32, message = "Username length must be between 6 and 32 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Username must contain only letters, numbers, and underscores")
    private String username;
    @Size(min = 6, max = 32, message = "Password length must be between 6 and 34 characters")
    private String password;
    private String phoneNumber;
    private String email;
}
