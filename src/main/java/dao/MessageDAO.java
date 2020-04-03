package dao;

import models.Dialog;
import models.Message;

import java.util.ArrayList;

public interface MessageDAO {
    void insert(Message message, int chatId);
    ArrayList<Message> getMessages(int chatId, int page);
    ArrayList<Dialog> getLastMessages(String userLogin);
}
