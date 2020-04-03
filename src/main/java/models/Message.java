package models;

public class Message {
    String idMessage;
    String author;
    String text;
    String date;

    public Message(String idMessage, String author, String text, String date) {
        this.idMessage = idMessage;
        this.author = author;
        this.text = text;
        this.date = date;
    }

    public String getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(String idMessage) {
        this.idMessage = idMessage;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
