package com.zonlinks.giantbing.guangzhouboard.Entity;

/**
 * Created by P on 2017/9/1.
 */

public class CheackUpdate {

    /**
     * AppName : null
     * ApkName : null
     * VerName : null
     * VerCode : null
     * FilePath : null
     * Update : 0
     * Message : 该设备没有注册
     * Result : false
     */

    private String AppName;
    private String ApkName;
    private String VerName;
    private String VerCode;
    private String FilePath;
    private int Update;
    private String Message;
    private boolean Result;

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String AppName) {
        this.AppName = AppName;
    }

    public String getApkName() {
        return ApkName;
    }

    public void setApkName(String ApkName) {
        this.ApkName = ApkName;
    }

    public String getVerName() {
        return VerName;
    }

    public void setVerName(String VerName) {
        this.VerName = VerName;
    }

    public String getVerCode() {
        return VerCode;
    }

    public void setVerCode(String VerCode) {
        this.VerCode = VerCode;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String FilePath) {
        this.FilePath = FilePath;
    }

    public int getUpdate() {
        return Update;
    }

    public void setUpdate(int Update) {
        this.Update = Update;
    }

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
