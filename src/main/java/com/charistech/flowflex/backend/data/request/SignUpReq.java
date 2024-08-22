package com.charistech.flowflex.backend.data.request;

import jakarta.validation.ValidationException;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import static com.charistech.flowflex.backend.utils.RequestValidator.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpReq {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String department;


    public void validateSignUpReq() {
        List<String> errors = new ArrayList<>();
        validateFirstName(firstName, errors);
        validateLastName(lastName, errors);
        validatePassword(password, errors);
        validateEmail(email, errors);
        validatePhoneNumber(phoneNumber, errors);
        validateDepartment(department, errors);
        if (!errors.isEmpty()) {
            throw new ValidationException(String.join(", ", errors));
        }
    }

}
