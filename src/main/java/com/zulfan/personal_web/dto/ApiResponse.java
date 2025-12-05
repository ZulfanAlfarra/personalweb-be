package com.zulfan.personal_web.dto;

import java.util.List;
import java.util.Map;

public class ApiResponse<T> {
    private boolean status;
    private Map<String, List<String>> errors;
    private String message;
    private T data;

    public ApiResponse() {}

    public ApiResponse(Boolean status,String message, T data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(Boolean status, Map<String, List<String>> errors, T data){
        this.status = status;
        this.errors = errors;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(String message, T data){
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> error(Map<String, List<String>> errors){
        return new ApiResponse<>(false, errors, null);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, List<String>> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
