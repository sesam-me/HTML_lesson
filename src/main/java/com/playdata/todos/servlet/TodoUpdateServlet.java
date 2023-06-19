package com.playdata.todos.servlet;

import com.playdata.todos.dao.TodoDao;
import com.playdata.todos.dto.TodoJoinUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class TodoUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getAttribute("todoid"));
        req.setAttribute("todoid", req.getParameter("todoid")); // Parameter : 값을 가져올 때
        int id = Integer.parseInt(req.getParameter("todoid"));
        req.setAttribute("content", new TodoDao().findById(id).getContent()); // setAttribute : view의 속성
        req.getRequestDispatcher("views/todoupdate.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("todoid"));
        String content = req.getParameter("content");

//        TodoDao todoDao = new TodoDao();
//        todoDao.update(id,content);

//        싱글톤
        TodoDao.getInstance().update(id,content);

        resp.sendRedirect("/main");
    }
}
