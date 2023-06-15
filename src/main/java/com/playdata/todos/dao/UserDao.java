package com.playdata.todos.dao;
import com.playdata.todos.config.JdbcConnection;
import com.playdata.todos.dto.User;
import javax.jws.soap.SOAPBinding;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserDao {
    public void insert(User user){
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "insert into users(username, password, name) values (?, ?, ?);";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, user.getUsername());
            psmt.setString(2, user.getPassword());
            psmt.setString(3, user.getName());
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean login(String username, String password){
        List<User> users = new ArrayList<User>();
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select * from users where username = ? and password = ?";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setString(1, username);
            psmt.setString(2, password);
            ResultSet resultSet = psmt.executeQuery();
            while (resultSet.next()){
                users.add(makeUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users.size() != 0;
    }
    private User makeUser(ResultSet resultSet){
        try {
            int id1 = resultSet.getInt("id");
            String password = resultSet.getString("password");
            String username = resultSet.getString("username");
            String name = resultSet.getString("name");
            String createAt = resultSet.getString("create_at");
            return new User(id1, username, name, password, createAt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}