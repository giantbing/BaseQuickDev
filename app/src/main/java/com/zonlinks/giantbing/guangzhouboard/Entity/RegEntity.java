package com.zonlinks.giantbing.guangzhouboard.Entity;

/**
 * Created by P on 2017/9/1.
 */

public class RegEntity {

    /**
     * Message : 设备已存在无法重复注册
     * Result : false
     */

    private String Message;
    private boolean Result;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public boolean isResult() {
        return Result;
    }

    public void setResult(boolean Result) {
        this.Result = Result;
    }
}
