package com.charistech.flowflex.backend.data.response;

import com.charistech.flowflex.backend.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder(value = {"isSuccessful","statusCode", "responseMessage", "time", "payLoad"})
public class APIResponse <T>{

    private boolean isSuccessful;
    private int statusCode;
    private String responseMessage;
    private String time;
    private T payLoad;


    public APIResponse(boolean isSuccessful, int statusCode, String responseMessage, T payLoad) {
        this.isSuccessful = isSuccessful;
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.time = DateUtil.saveDate(LocalDateTime.now());
        this.payLoad = payLoad;
    }
}
