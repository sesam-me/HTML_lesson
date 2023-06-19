package com.playdata.todos.servlet;

import com.playdata.todos.config.History;
import com.playdata.todos.dao.UserDao;
import com.playdata.todos.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        History.setHistory(req, resp);
        resp.setStatus(200);
        req.getRequestDispatcher("views/user.html").forward(req, resp);
        super.doGet(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
//        System.out.println(username); // insert
//        System.out.println(password);
//        System.out.println(name);

        User user = new User(null,username,password,name,null);
        UserDao userDao = new UserDao();
        userDao.insert(user);

        resp.setStatus(201);
        resp.sendRedirect("/login"); //  doPost -> doPost에서 받은 값을 sendRedirect로 보내서 -> doGet으로 받아줌
//        super.doPost(req, resp);
    }
}

