package cn.sixlab.mine.dao.utils;

import lombok.Data;

import java.util.Map;

@Data
public class JsonResult {
    private int status;
    private String message;
    private Map data;

    public static JsonResult result(int status, String message, Map data) {
        JsonResult result = new JsonResult();
        result.status = status;
        result.message = message;
        result.data = data;
        return result;
    }

    public static JsonResult success() {
        return result(200, null, null);
    }

    public static JsonResult success(String message) {
        return result(200, message, null);
    }

    public static JsonResult success(String message, Map data) {
        return result(200, message, data);
    }

    public static JsonResult success(Map data) {
        return result(200, null, data);
    }

    public static JsonResult error(int status, String message) {
        return result(status, message, null);
    }

    public static JsonResult error(String message) {
        return result(0, message, null);
    }
}
