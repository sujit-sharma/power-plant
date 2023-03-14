package com.sujit.handler;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class ApiErrorListResponse {

    private String message;

    private List<ApiErrorResponse> errors;

    public ApiErrorListResponse addErrorItem(ApiErrorResponse errorItem) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(errorItem);
        return this;
    }
}
