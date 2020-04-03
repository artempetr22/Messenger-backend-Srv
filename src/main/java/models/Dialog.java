package models;

public class Dialog {
    String chatId;
    String user;
    String member;
    Message lastMessage;

    public Dialog(String chatId, String user, String member) {
        this.chatId = chatId;
        this.user = user;
        this.member = member;
    }

    public Dialog(String chatId, String user, String member, Message lastMessage) {
        this.chatId = chatId;
        this.user = user;
        this.member = member;
        this.lastMessage = lastMessage;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }
}
