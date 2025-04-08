package com.mks.crescentbites.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Builder
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String rootExceptionMessage;
    private String errorCode;
    private String requestUrl;
    private String requestType;
    private String details;
}
