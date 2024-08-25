package com.charistech.flowflex.backend.utils;

import com.charistech.flowflex.backend.model.user.AppUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FlowFlexUtils {

    public static final int TOEKN_EXPIRY_DATE = 60 * 24;

    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z]{2,6}$";

    public static String saveDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm:ss a");
        return date.format(formatter);
    }

    public static String applicationUrl(HttpServletRequest request){
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
    public static String frontEndAppUrl(HttpServletRequest request){
        return "http://"+request.getServerName()+":9092"+request.getContextPath();
    }

    protected AppUser getUserFromContext(){
        return (AppUser) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
    }

    protected String getUserName(){
        return SecurityContextHolder.getContext().getAuthentication()
                .getName();
    }
}
