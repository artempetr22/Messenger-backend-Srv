package ru;

public class DialogForm {
    private String from;
    private String to;
    private String page;

    public DialogForm(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public DialogForm(String from, String to, String page) {
        this.from = from;
        this.to = to;
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
