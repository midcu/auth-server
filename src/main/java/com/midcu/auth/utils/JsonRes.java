package com.midcu.auth.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class JsonRes {

    private String message;

    private Object data;

    private Integer status;

    public static String FIND = "查询成功！";
    public static String SAVE = "保存成功！";
    public static String UPDATE = "更新成功！";
    public static String DELETE = "删除成功！";

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    JsonRes(String message, Integer status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public static JsonRes OK(String message) {
        return new JsonRes(message, 200, null);
    }

    public static JsonRes OK(String message, Object data) {
        return new JsonRes(message, 200, data);
    }

    public static JsonRes Bad(String message) {
        return new JsonRes(message, 400, null);
    }

    public static JsonRes Bad(String message, Object data) {
        return new JsonRes(message, 400, data);
    }

    public static JsonRes Err(String message) {
        return new JsonRes(message, 500, null);
    }

    public static JsonRes Err(String message, Object data) {
        return new JsonRes(message, 500, data);
    }

    public static JsonRes Status(String message, Integer status) {
        return new JsonRes(message, status, null);
    }

    public static JsonRes Status(String message, Integer status, Object data) {
        return new JsonRes(message, status, data);
    }

    public String toMessageString() {
        return "{\"message\":\"" + this.message + "\",\"status\":" + this.status + ",\"timestamp\":\"" + this.timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\"}";
    }
}
