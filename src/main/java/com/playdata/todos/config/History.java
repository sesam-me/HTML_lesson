package com.playdata.todos.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class History {
    public static void setHistory(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        // getRequestURI() : 현재 요청된 URI (Uniform Resource Identifier)를 반환합니다.
        // URI는 웹 애플리케이션에서 클라이언트가 요청한 리소스의 경로 및 식별자를 나타냅니다.
        String requestURI = req.getRequestURI();
        String history = "history";
        int index = 0;
        if (cookies == null) {
            resp.addCookie(new Cookie(history + index, requestURI));
            return;
        }
        int max = 0;
        for (int i = 0; i < cookies.length; i++) {
            Cookie c = cookies[i];
            if (c.getName().contains(history)
                    && index < Integer.parseInt(
                    c.getName().replace(history, "")
            )) {
                index = Integer.parseInt(
                        c.getName().replace(history, "")
                );
                max = i;
            }
        }
        index++;
//        if (!cookies[max].getValue().equals(requestURI))
            resp.addCookie(new Cookie(history + index, requestURI));
    }



//    뒤로가기
    public static void back(HttpServletRequest req, HttpServletResponse resp){
        History.back(req, resp);
    }
}