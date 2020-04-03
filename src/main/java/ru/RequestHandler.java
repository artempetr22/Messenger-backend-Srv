package ru;

import dao.JdbcMessageDAO;
import dao.JdbcUserDAO;
import models.Dialog;
import models.Message;
import models.User;

import java.util.ArrayList;

public class RequestHandler {
    static ArrayList<Message> getMessages(DialogForm dialogForm){
        JdbcMessageDAO jdbcMessageDAO = new JdbcMessageDAO();
        return jdbcMessageDAO.getMessages(hash(dialogForm.getFrom(), dialogForm.getTo()), Integer.parseInt(dialogForm.getPage()));
    }

    static ServerResponse sendMessage(MessageForm messageForm){

        try {
            //send message to database
            JdbcMessageDAO jdbcMessageDAO = new JdbcMessageDAO();

            jdbcMessageDAO.insert(new Message("", messageForm.getFrom(), messageForm.getText(), messageForm.getDate()), hash(messageForm.getFrom(), messageForm.getTo()));
        }
        catch (Exception e){
            e.printStackTrace();
            return new ServerResponse("send message", "no", "101", "bad database connection");
        }

        return new ServerResponse("send message", "yes", "100", "message was sent successfully");
    }

    static ServerResponse createDialog(DialogForm dialogForm){
        String from = dialogForm.getFrom();
        String to = dialogForm.getTo();

        try {
            //create dialogForm
            JdbcUserDAO userDAO = new JdbcUserDAO();
            userDAO.startChat(dialogForm, hash(dialogForm.getFrom(), dialogForm.getTo()));

            System.out.println("-> dialogForm created: " + dialogForm.getFrom() + " & " + dialogForm.getTo());
        }
        catch (Exception e){
            e.printStackTrace();
            return new ServerResponse("create dialogForm", "no", "201", "bad database connection");
        }

        return new ServerResponse("create dialogForm", "yes", "200", "dialogForm was successfully created");
    }

    static ServerResponse register(RegisterForm form){

        try {
            //register user
            //add user to users (form.login -> user.id, form.name -> user.name, form.phone -> user.phone)
            //create table [form.login]_chats(chat_id, member)

            JdbcUserDAO userDAO = new JdbcUserDAO();
            User user = new User(form.getLogin(), form.getName(), form.getPhone());

            userDAO.insert(user);
            userDAO.createChatsTable(user);

            System.out.println("-> user " + form.getLogin() + " was registered");
        }
        catch (Exception e){
            return new ServerResponse("register user", "no", "301", "bad database connection");
        }

        return new ServerResponse("register user", "yes", "300", "user was successfully registered");
    }

    static ArrayList<Dialog> getDialogs(String user){
        JdbcMessageDAO jdbcMessageDAO = new JdbcMessageDAO();
        return jdbcMessageDAO.getLastMessages(user);
    }

    static ServerResponse login(String user){
        JdbcUserDAO jdbcUserDAO = new JdbcUserDAO();

        User logged = jdbcUserDAO.findByUserId(user);

        System.out.println("-> user " + user + " was logged in");

        if (logged != null) return new ServerResponse("login user", "yes", "500", "user exists and logged in");

        return new ServerResponse("login user", "no", "501", "bad database connection");
}

    private boolean checkUsers(){
        //check if both users exist
        if(false){
            return false;
        }

        return true;
    }

    private static int hash(String user1, String user2){
        if (user1.hashCode() > user2.hashCode()) return (user1+user2).hashCode();
        else return (user2+user1).hashCode();
    }
}
