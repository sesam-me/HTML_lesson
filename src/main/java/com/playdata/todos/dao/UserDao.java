package com.playdata.todos.dao;
import com.playdata.todos.config.JdbcConnection;
import com.playdata.todos.dto.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UserDao {
    public static User me;
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
    public User login(String username, String password){
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
        if (users.size() != 0) { // size가 0이 아니라면(users에 로그인한 사람이 있다면 1명, 어차리 로그인하는 사람은 1명)
            me=users.get(0); // 첫번째 사람을 me에 넣어라(어차피 1명)
//            new LogoutThread().start(); // sleep기간동안 움직이 않으면, 자동으로 로그아웃
            return users.get(0);

        }
        return null;
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