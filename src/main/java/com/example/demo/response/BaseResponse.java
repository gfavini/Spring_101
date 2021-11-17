package com.example.demo.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse <T>{
    private int httpCode;
    private String message;
    private T response;
}
