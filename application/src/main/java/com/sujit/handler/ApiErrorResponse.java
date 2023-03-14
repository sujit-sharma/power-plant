package com.sujit.handler;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ApiErrorResponse {

    private String violator;
    private String message;

}
