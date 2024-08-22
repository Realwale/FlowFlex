package com.charistech.flowflex.backend.utils;

import java.util.List;
import java.util.regex.Pattern;

import static com.charistech.flowflex.backend.utils.UserUtils.EMAIL_REGEX;

public class RequestValidator {

    public static void validateFirstName(String firstName, List<String> errors) {
        if (firstName == null || firstName.trim().isEmpty()) {
            errors.add("First name is missing or empty");
        }
    }

    public static void validateLastName(String lastName, List<String> errors) {
        if (lastName == null || lastName.trim().isEmpty()) {
            errors.add("Last name is missing or empty");
        }
    }


    public static void validatePassword(String password, List<String> errors) {
        if (password == null || password.trim().isEmpty()) {
            errors.add("Password is missing or empty");
        } else {
            if (password.length() < 8) {
                errors.add("Password must be at least 8 characters long");
            }
            if (!password.matches(".*[A-Z].*")) {
                errors.add("Password must contain at least one uppercase letter");
            }
            if (!password.matches(".*[a-z].*")) {
                errors.add("Password must contain at least one lowercase letter");
            }
            if (!password.matches(".*\\d.*")) {
                errors.add("Password must contain at least one digit");
            }
            if (!password.matches(".*[!@#$%^&*()].*")) {
                errors.add("Password must contain at least one special character (e.g., !@#$%^&*())");
            }
        }
    }

    public static void validateEmail(String email, List<String> errors) {
        Pattern pat = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        if (email == null || !pat.matcher(email).matches()) {
            errors.add("Invalid email");
        }
    }


    public static void validatePhoneNumber(String phoneNumber, List<String> errors) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            errors.add("Phone number is missing or empty");
        } else if (!phoneNumber.matches("^[+]?[0-9]{10,15}$")) {
            errors.add("Invalid phone number format");
        }
    }

    public static void validateDepartment(String Department, List<String> errors) {
        if (Department == null || Department.trim().isEmpty()) {
            errors.add("First name is missing or empty");
        }
    }
}
