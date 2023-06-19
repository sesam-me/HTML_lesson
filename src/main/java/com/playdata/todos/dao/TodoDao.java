package com.playdata.todos.dao;

import com.playdata.todos.config.JdbcConnection;
import com.playdata.todos.dto.TodoJoinUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {

//    싱글톤
    private static final TodoDao instance = new TodoDao();
    public static TodoDao getInstance (){
        return instance;
    }


    public List<TodoJoinUser> findAll(){
        List<TodoJoinUser> todoJoinUsers = new ArrayList<TodoJoinUser>();
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select\n" +
                "    t.id,\n" +
                "    t.create_at ,\n" +
                "    t.content,\n" +
                "    t.checked,\n" +
                "    u.name,\n" +
                "    u.id uid\n" +
                "from todos.todos as t\n" +
                "inner join todos.users as u\n" +
                "    on t.user_id = u.id";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                todoJoinUsers.add(
                        new TodoJoinUser(
                                resultSet.getInt("id"),
                                resultSet.getString("content"),
                                resultSet.getString("create_at"),
                                resultSet.getBoolean("checked"),
                                resultSet.getString("name"),
                                resultSet.getInt("uid")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return todoJoinUsers;
    }



    public TodoJoinUser insert(int uid, String content){
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "insert into todos(user_id, content, checked) values (?, ?, false);";
        try {
            PreparedStatement psmt = conn.prepareStatement(sql);
            psmt.setInt(1, uid);
            psmt.setString(2, content);
            psmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public TodoJoinUser findById(int id){
        List<TodoJoinUser> todoJoinUsers = new ArrayList<TodoJoinUser>();
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select\n" +
                "    t.id,\n" +
                "    t.create_at,\n" +
                "    t.content,\n" +
                "    t.checked,\n" +
                "    u.name,\n" +
                "    u.id uid \n" +
                "from todos.todos as t\n" +
                "inner join todos.users as u\n" +
                "    on t.user_id = u.id\n" +
                "where t.id";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                todoJoinUsers.add(
                        new TodoJoinUser(
                                resultSet.getInt("id"),
                                resultSet.getString("content"),
                                resultSet.getString("create_at"),
                                resultSet.getBoolean("checked"),
                                resultSet.getString("name"),
                                resultSet.getInt("uid")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return todoJoinUsers.get(0);
    }


    public void update(int id, String content){
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "update todos set content = ? where id =?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, content);
            pst.setInt(2, id);
            pst.executeUpdate(); // resultset(->쿼리사용)이 안나올 때, executeUpdate()사용
//            select가 아니면, 거의 executeQuery() 사용 -> resultset 사용
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    public List<TodoJoinUser> keyword(String content){

        List<TodoJoinUser> list = new ArrayList<TodoJoinUser>();
        Connection conn = new JdbcConnection().getJdbc();
        String sql = "select * from todos " +
                "join users u on u.id=todos.user_id " +
                "where content like ?";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, "%"+content+"%");
            // resultset(->쿼리사용, 이건 insert?할때?)이 안나올 때, executeUpdate()사용
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                list.add(
                        new TodoJoinUser(
                                resultSet.getInt("id"),
                                resultSet.getString("content"),
                                resultSet.getString("create_at"),
                                resultSet.getBoolean("checked"),
                                resultSet.getString("name"),
                                resultSet.getInt("user_id")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }


}
