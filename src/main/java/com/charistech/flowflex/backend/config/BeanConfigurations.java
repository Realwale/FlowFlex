package com.charistech.flowflex.backend.config;

import com.charistech.flowflex.backend.constant.MailConstants;
import com.charistech.flowflex.backend.security.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurations {

    private final CustomUserDetailService userDetailService;

    private final ApplicationContext applicationContext;

    private final MailConstants mailConstants;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return  authenticationProvider;
    }

    @Bean
    public SpringResourceTemplateResolver resourceTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(applicationContext);
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setDefaultEncoding("utf-8");
        javaMailSender.setHost(mailConstants.getMailHost());
        javaMailSender.setPort(mailConstants.getMailPort());
        javaMailSender.setUsername(mailConstants.getMailUsername());
        javaMailSender.setPassword(mailConstants.getMailPassword());

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return javaMailSender;
    }
}
