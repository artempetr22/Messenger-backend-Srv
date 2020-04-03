package dao;

import models.User;
import ru.DialogForm;

import java.sql.*;
import javax.sql.DataSource;

public class JdbcUserDAO implements UserDAO {
    private DataSource dataSource;


    public void insert(User user){

        String sql = "INSERT INTO Users (id, name, phone) VALUES (?, ?, ?)";
        Connection conn = null;

        try {
            conn = DBConfigs.getDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPhone());
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

    public User findByUserId(String id){

        String sql = "SELECT * FROM Users WHERE id = ?";

        Connection conn = null;

        try {
            conn = DBConfigs.getDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            User user = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("id"), rs.getString("name"), rs.getString("phone"));
            }
            else return null;
            rs.close();
            ps.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }

    public void createChatsTable(User user) {
        String sql = "CREATE TABLE " + user.getId()+"_chats" + " (`chat_id` VARCHAR(45) NOT NULL, `member` VARCHAR(45) NOT NULL, PRIMARY KEY (`chat_id`))";

        Connection conn = null;

        try {
            conn = DBConfigs.getDbConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
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

    //adds chatId and member to each users chats Tables and creates chat table "hatId"
    public void startChat(DialogForm dialogForm, int chatId) {
        User user1 = findByUserId(dialogForm.getFrom());
        User user2 = findByUserId(dialogForm.getTo());

        String sql1 = "INSERT INTO " + user1.getId()+ "_chats" + " (`chat_id`, `member`) VALUES (?, ?)";
        String sql2 = "INSERT INTO " + user2.getId()+ "_chats" + " (`chat_id`, `member`) VALUES (?, ?)";

        String sql3 = "CREATE TABLE " + "Chat_" + chatId + " (`idMessage` INT NOT NULL AUTO_INCREMENT, `author` VARCHAR(45) NOT NULL, `text` VARCHAR(45) NOT NULL, `date` DATETIME NOT NULL, PRIMARY KEY (`idMessage`))";

        Connection conn = null;



        try {
            conn = DBConfigs.getDbConnection();
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ps1.setInt(1, chatId);
            ps1.setString(2, user2.getId());

            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, chatId);
            ps2.setString(2, user1.getId());

            PreparedStatement ps3 = conn.prepareStatement(sql3);

            ps1.executeUpdate();
            ps1.close();

            ps2.executeUpdate();
            ps2.close();

            ps3.executeUpdate();
            ps3.close();

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
}
