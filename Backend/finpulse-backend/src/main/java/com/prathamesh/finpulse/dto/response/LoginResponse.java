package com.prathamesh.finpulse.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {

    private Long id;
    private String fullName;
    private String email;
    private String role;
    private String message;
}
