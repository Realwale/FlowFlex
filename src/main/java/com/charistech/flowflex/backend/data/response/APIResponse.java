package com.charistech.flowflex.backend.data.response;

import lombok.*;

import java.time.LocalDateTime;

import static com.charistech.flowflex.backend.utils.FlowFlexUtils.saveDate;


@Getter
@Setter
@Builder
@NoArgsConstructor
public class APIResponse {

    private boolean isSuccessful;
    private int statusCode;
    private String responseMessage;
    private String time;
    private Object payLoad;

    public APIResponse(boolean isSuccessful, int statusCode, String responseMessage, String time, Object payLoad) {
        this.isSuccessful = isSuccessful;
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.time = saveDate(LocalDateTime.now());
        this.payLoad = payLoad;
    }
}
