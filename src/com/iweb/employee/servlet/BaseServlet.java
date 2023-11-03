package com.iweb.employee.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

//简化Servlet开发
public class BaseServlet extends HttpServlet {

    private String method;

    //所有的get和post都会进入到service方法中
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //获取方法名称
        String methodName = req.getParameter("method");
        try {
            Method method = this.getClass().getMethod(methodName,
                    HttpServletRequest.class
                    , HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
