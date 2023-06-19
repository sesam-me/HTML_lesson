package com.playdata.todos.servlet;

import com.playdata.todos.config.History;
import com.playdata.todos.dao.UserDao;
import com.playdata.todos.dto.User;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        History.setHistory(req, resp);
        req.getRequestDispatcher("views/login.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Post요청이 왔을 때
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new UserDao().login(username,password);
//        Cookie cookie = new Cookie("uid", user.getId().toString());
//        Cookie cookie2 = new Cookie("uname", user.getName().toString());
//        cookie.setMaxAge(10); // 만들어진 순간부터 10초동안 살아있다.
//        resp.addCookie(cookie);
//        resp.addCookie(cookie2);

//        setAttribute : 객체에 이름과 값을 지정하여 속성을 저장하는 역할
        HttpSession session = req.getSession();
        session.setAttribute("uname", user.getName()); // uname의 속성을 user.getName()으로 설정
        session.setAttribute("uid", user.getId()); // uname의 속성을 user.getName()으로 설정

        if(user != null) {
            resp.sendRedirect("/main");
        } else{
            resp.sendRedirect("/login");
        }
    }
}
