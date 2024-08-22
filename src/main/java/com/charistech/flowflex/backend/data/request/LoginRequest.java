package com.charistech.flowflex.backend.data.request;


import jakarta.validation.ValidationException;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static com.charistech.flowflex.backend.utils.RequestValidator.validateEmail;
import static com.charistech.flowflex.backend.utils.RequestValidator.validatePassword;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String password;

    public void validateLoginRequest() {
        List<String> errors = new ArrayList<>();
        validateEmail(email, errors);
        validatePassword(password, errors);
        if (!errors.isEmpty()) {
            throw new ValidationException(String.join(", ", errors));
        }
    }
}