package com.charistech.flowflex.backend.controller.auth;


import com.charistech.flowflex.backend.data.request.LoginRequest;
import com.charistech.flowflex.backend.data.request.SignUpReq;
import com.charistech.flowflex.backend.data.response.APIResponse;
import com.charistech.flowflex.backend.service.auth.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<APIResponse> createAccount(@RequestBody SignUpReq signUpReq){
        signUpReq.validateSignUpReq();
        return new ResponseEntity<>(authService.createAccount(signUpReq), HttpStatus.CREATED);
    }


    @PostMapping("/verify")
    public ResponseEntity<APIResponse> verifyAccount(@RequestParam(value = "a-v-t") String token, HttpServletRequest request){
        return new ResponseEntity<>(authService.confirmEmail(token, request), HttpStatus.OK);
    }

    @PostMapping("/resend-link")
    public ResponseEntity<APIResponse> requestNewVerification(@RequestBody String email, HttpServletRequest request){
        return new ResponseEntity<>(authService.resendConfirmationLink(email, request), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<APIResponse> authenticateLogin(@RequestBody LoginRequest request){
        request.validateLoginRequest();
        return new ResponseEntity<>(authService.authenticateLogin(request), HttpStatus.OK);
    }
}
