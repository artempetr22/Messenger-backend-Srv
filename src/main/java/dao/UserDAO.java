package dao;

import models.User;
import ru.DialogForm;

public interface UserDAO {
    void insert(User user);
    User findByUserId(String id);
    void createChatsTable(User user);
    void startChat(DialogForm dialogForm, int chatId);
}
