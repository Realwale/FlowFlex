package com.charistech.flowflex.backend.mail.common;

import com.charistech.flowflex.backend.constant.EventType;
import com.charistech.flowflex.backend.model.user.AppUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;


@Getter
@Setter
public class OnApplicationEvent extends ApplicationEvent {

    private AppUser user;
    private String token;
    private EventType type;


    public OnApplicationEvent(AppUser user, String token, EventType type) {
        super(user);
        this.user = user;
        this.token = token;
        this.type = type;
    }
}