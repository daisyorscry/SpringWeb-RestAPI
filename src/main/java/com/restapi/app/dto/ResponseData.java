package com.restapi.app.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseData <T>
{
    private T data;
    private Map<String, String> errors;
    private boolean success;
    private String message;

}
