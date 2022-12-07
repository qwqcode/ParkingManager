package com.qwqcode.parkingmanager.model.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.lang.Nullable;

public class CommonResp {
    public static CommonResp Success(String msg) {
        return new CommonResp(true);
    }

    public static CommonResp Error(String msg) {
        return new CommonResp(false, msg);
    }

    public static CommonResp Data(Object data) {
        return new CommonResp(true, data);
    }

    private boolean success;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public CommonResp() {
        this.success = true;
    }

    public CommonResp(boolean success) {
        this.success = success;
    }

    public CommonResp(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public CommonResp(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public CommonResp(boolean success, String msg, Object data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
