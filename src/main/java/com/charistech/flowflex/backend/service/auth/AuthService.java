package com.charistech.flowflex.backend.service.auth;

import com.charistech.flowflex.backend.data.response.APIResponse;
import com.charistech.flowflex.backend.model.user.AppUser;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

    void saveEmailConfirmToken(AppUser user);

    APIResponse confirmEmail(String token, HttpServletRequest request);

    APIResponse resendConfirmationLink(String email, HttpServletRequest request);
}
