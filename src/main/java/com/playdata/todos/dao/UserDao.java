package com.playdata.todos.dao;

import com.mysql.cj.jdbc.ConnectionImpl;
import com.playdata.todos.config.JdbcConnection;
import com.playdata.todos.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao { //DataaccessObject
    public void insert(User user){
        Connection conn  = new JdbcConnection().getJdbc();

        String sql = "insert into suer(username, password, name) " + "Values(?,?,?)";

        try{
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            pst.setString(3, user.getName());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
