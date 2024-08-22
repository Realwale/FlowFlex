package com.charistech.flowflex.backend.service.auth;


import com.charistech.flowflex.backend.constant.EventType;
import com.charistech.flowflex.backend.data.response.APIResponse;
import com.charistech.flowflex.backend.exception.InvalidArgumentException;
import com.charistech.flowflex.backend.exception.ResourceAlreadyExistsException;
import com.charistech.flowflex.backend.exception.ResourceNotFoundException;
import com.charistech.flowflex.backend.mail.common.OnApplicationEvent;
import com.charistech.flowflex.backend.model.token.JwtToken;
import com.charistech.flowflex.backend.model.user.AppUser;
import com.charistech.flowflex.backend.repository.AppUserRepository;
import com.charistech.flowflex.backend.repository.TokenRepository;
import com.charistech.flowflex.backend.security.JwtService;
import com.charistech.flowflex.backend.utils.EmailUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final TokenRepository tokenRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final AppUserRepository userRepository;

    private static final int EXPIRY_DATE = 60 * 24;

    @Override
    public void saveEmailConfirmToken(AppUser user) {
        String token = UUID.randomUUID().toString();
        JwtToken verificationToken = new JwtToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(calculateExpiryDate());
        tokenRepository.save(verificationToken);
        eventPublisher.publishEvent(new OnApplicationEvent(user, token, EventType.Registration));
    }

    @Override
    public APIResponse confirmEmail(String token, HttpServletRequest request) {
        JwtToken confirmationToken = tokenRepository.findByToken(token)
                .orElseThrow(()-> new ResourceNotFoundException("Null confirmation link"));

        AppUser user = confirmationToken.getUser();

        if (Objects.isNull(user)){
            throw new ResourceNotFoundException("User not found with email");
        }

        if(user.isVerified()){
            throw new ResourceAlreadyExistsException("Your account is already verified. Proceed to login.");
        }

        if (confirmationToken.getExpiryDate().before(new Date())){
            tokenRepository.delete(confirmationToken);
            throw new InvalidArgumentException("Token is expired. Please request a new verification link at: "
                    + EmailUtils.applicationUrl(request)+"/api/v1/account/new-verification-link?email="+user.getEmail());
        }
        user.setVerified(true);
        userRepository.save(user);

        return APIResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(false)
                .responseMessage("Email successfully verified")
                .payLoad(user.getEmail())
                .build();
    }


    @Override
    public APIResponse resendConfirmationLink(String email, HttpServletRequest request) {
        AppUser appUser = userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("Account not found"));

        if (appUser.isVerified()) {
            throw new ResourceAlreadyExistsException("Account already verified, Proceed to login");
        }

        if(tokenRepository.existsByUser(appUser)){
            tokenRepository.delete(tokenRepository.findByUser(appUser));
        }
        eventPublisher.publishEvent(new OnApplicationEvent(appUser, EmailUtils.applicationUrl(request), EventType.Resend_Confirmation_Link));


        return APIResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .isSuccessful(false)
                .responseMessage("Please check your mail for a new verification link")
                .payLoad(appUser.getEmail())
                .build();
    }

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, EXPIRY_DATE);
        return new Date(cal.getTime().getTime());
    }

}
