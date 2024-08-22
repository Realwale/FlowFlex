package com.charistech.flowflex.backend.constant;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class MailConstants {

    @Value("${spring.mail.host}")
    private String mailHost;

    @Value("${spring.mail.port}")
    private int mailPort;

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Value("${spring.mail.password}")
    private String mailPassword;

    public static final String REGISTRATION_EMAIL_CONFIRMATION_SUBJECT= "Email Confirmation";
    public static final String RESET_PASSWORD_EMAIL_SUBJECT= "Reset password";
    public static final String INVITE_USER_REGISTRATION_EMAIL_SUBJECT= "You're Invited! Set Up Your Account for EntryGenius App";
    public static final String RESEND_CONFIRMATION_MAIL_SUBJECT= "New Confirmation Link";

}
