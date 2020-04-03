package ru;

public class ServerResponse {
    private String action;
    private String success;
    private String code;
    private String text;

    public ServerResponse(String action, String success, String code, String text) {
        this.action = action;
        this.success = success;
        this.code = code;
        this.text = text;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
