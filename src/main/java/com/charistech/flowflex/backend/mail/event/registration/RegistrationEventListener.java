package com.charistech.flowflex.backend.mail.event.registration;


import com.charistech.flowflex.backend.constant.EventType;
import com.charistech.flowflex.backend.constant.MailConstants;
import com.charistech.flowflex.backend.mail.common.OnApplicationEvent;
import com.charistech.flowflex.backend.mail.common.SendEmail;
import com.charistech.flowflex.backend.utils.EmailUtils;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

import static com.charistech.flowflex.backend.constant.UrlConstants.BASE_URL;

@Component
@RequiredArgsConstructor
public class RegistrationEventListener implements ApplicationListener<OnApplicationEvent> {

    private final HttpServletRequest request;

    private final SendEmail sendEmail;

    @Override
    public void onApplicationEvent(OnApplicationEvent event) {
        try {
            if (event.getType() == EventType.Registration) {
                confirmRegistration(event);
            } else if (event.getType() == EventType.Invitation) {
                inviteUser(event);
            }else if (event.getType() == EventType.Resend_Confirmation_Link) {
                resendLink(event);
            }
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }

    private void confirmRegistration(OnApplicationEvent event) throws MessagingException,
            UnsupportedEncodingException {

        String subject = MailConstants.REGISTRATION_EMAIL_CONFIRMATION_SUBJECT;
        String urlVariableName = "confirmationEmailUrl";
        String templateName = "accountConfirmationTemplate";
        String recipientEmail = event.getUser().getEmail();
        String url = EmailUtils.frontEndAppUrl(request) + BASE_URL+ "/account/registration/verify?t=" +
                event.getToken()+"&email="+recipientEmail;

        sendEmail.send(recipientEmail, event.getUser().getFirstName(),
                subject, templateName, urlVariableName, url);

    }

    private void inviteUser(OnApplicationEvent event) throws MessagingException,
            UnsupportedEncodingException {

        String subject = MailConstants.INVITE_USER_REGISTRATION_EMAIL_SUBJECT;
        String urlVariableName = "inviteUserEmailUrl";
        String templateName = "inviteUserEmail";
        String recipientEmail = event.getUser().getEmail();
        String url = EmailUtils.frontEndAppUrl(request) + BASE_URL+ "/account/registration/set-password?t=" +
                event.getToken()+"&email="+recipientEmail;

        sendEmail.send(recipientEmail, event.getUser().getFirstName(),
                subject, templateName, urlVariableName, url);
    }

    private void resendLink(OnApplicationEvent event) throws MessagingException,
            UnsupportedEncodingException {

        String subject = MailConstants.RESEND_CONFIRMATION_MAIL_SUBJECT;
        String urlVariableName = "newConfirmationEmailUrl";
        String templateName = "newConfirmationEmail";
        String recipientEmail = event.getUser().getEmail();
        String url = EmailUtils.frontEndAppUrl(request) + BASE_URL+ "/account/registration/verify?t=" +
                event.getToken()+"&email="+recipientEmail;

        sendEmail.send(recipientEmail, event.getUser().getFirstName(),
                subject, templateName, urlVariableName, url);
    }
}