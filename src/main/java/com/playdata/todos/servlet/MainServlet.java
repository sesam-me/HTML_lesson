package com.playdata.todos.servlet;

import com.playdata.todos.config.History;
import com.playdata.todos.dao.TodoDao;
import com.playdata.todos.dao.UserDao;
import com.playdata.todos.dto.TodoJoinUser;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class MainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Object uname = session.getAttribute("uname");
        req.setAttribute("uname", uname);

        String keyword = req.getParameter("keyword");

        if(keyword==null){ // keyword가 null이면, 다 뽑고
            req.setAttribute("todolist", new TodoDao().findAll());
        }
        else{  // 있으면 해당되는 것만 뽑아
            List<TodoJoinUser> key_list = TodoDao.getInstance().keyword(keyword);
            req.setAttribute("todolist", key_list);
        }


        req.getRequestDispatcher("views/main.jsp").forward(req,resp);



        //        History.setHistory(req, resp);
//
////        Cookie[] cookies = req.getCookies();
//        String uid = null;
//        String uname = null;
////        for (Cookie c : cookies) {
////            if (c.getName().equals("uid")) uid = c.getValue();
////            if (c.getName().equals("uname")) uname = c.getValue();
////        }
//
//        if(UserDao.me!=null) {
//            HttpSession session = req.getSession();
//            resp.setContentType("text/html;charset=UTF-8");
//            PrintWriter writer = resp.getWriter(); // getWriter는 html대신 사용
//            writer.println("<!DOCTYPE html>\n" +
//                    "<html lang=\"en\">\n" +
//                    "<head>\n" +
//                    "    <meta charset=\"UTF-8\">\n" +
//                    "    <title>Main</title>\n" +
//                    "</head>\n" +
//                    "<body>\n" +
//                    "  <h1> " + uname + " " + session.getAttribute(uname) + " 환영합니다.</h1>\n" +
////                    "  <h1> " + UserDao.me.getCreateAt() + " 가입했습니다.</h1>\n"  +
//                   " <a href =\"/back\">back</a>\n" +
//                   " <a href =\"/todos\">+</a>\n" +
//                   " <a href =\"/userupdate\">userupdate</a>\n" +
//                    "</body>\n" +
//                    "</html>");
//            writer.close();
//        }else {
//            resp.sendRedirect("/login"); // 로그인안하고 주소로 들어왔을 때, login화면으로 가게 만듦
//        }



    }
}
