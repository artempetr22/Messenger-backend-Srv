package dao;



import models.Dialog;
import models.Message;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JdbcMessageDAO implements MessageDAO {
    private DataSource dataSource;


    public void insert(Message message, int chatId){

        String sql = "INSERT INTO " + "Chat_" + chatId + " (author, text, date) VALUES (?, ?, ?)";
        Connection conn = null;

        try {
            SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat format2= new SimpleDateFormat("HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());

            conn = DBConfigs.getDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, message.getAuthor());
            ps.setString(2, message.getText());
            ps.setString(3, format1.format(date)+"T"+format2.format(date));
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    public ArrayList<models.Message> getMessages(int chatId, int page) {
        Connection conn = null;

        String sql = "SELECT * FROM " + "Chat_" + chatId + " ";


        ArrayList<Message> messages = new ArrayList<>();

        try {
            conn = DBConfigs.getDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                messages.add(new Message(rs.getString("idMessage"), rs.getString("author"), rs.getString("text"), rs.getString("date")));
            }
            rs.close();
            ps.close();
            return messages;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    public ArrayList<Dialog> getLastMessages(String userLogin) {
        String sql1 = "SELECT * FROM " + userLogin + "_chats";
        ArrayList<Dialog> dialogs = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConfigs.getDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql1);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dialogs.add(new Dialog(rs.getString("chat_id"), userLogin, rs.getString("member")));
            }
            rs.close();
            ps.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }

        dialogs.forEach(dialog -> {
            String sql2 = "SELECT * FROM " + "Chat_" + dialog.getChatId() + " ORDER BY idMessage DESC LIMIT 1";

            Connection conn1 = null;
            try {
                conn1 = DBConfigs.getDbConnection();
                PreparedStatement ps = conn1.prepareStatement(sql2);

                ResultSet rs = ps.executeQuery();

                if (rs.next()){
                    dialog.setLastMessage(new Message(rs.getString("idMessage"), rs.getString("author"), rs.getString("text"), rs.getString("date")));
                }

                rs.close();
                ps.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });

        return dialogs;
    }
}
