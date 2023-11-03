package com.iweb.employee.servlet;

import com.iweb.employee.pojo.User;
import com.iweb.employee.service.UserService;
import com.iweb.employee.service.impl.UserServiceImpl;
import com.iweb.employee.util.FormToBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends BaseServlet{

    private UserService userService =
            new UserServiceImpl();

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //获取用户输入的验证码
        String code = req.getParameter("code");
        //获取正确的验证码
        HttpSession session = req.getSession();

        String correctCode = (String) session.getAttribute("code");
        if(!correctCode.equalsIgnoreCase(code)){
            //用户输入的验证码不正确，给用加以提示信息
            req.setAttribute("msg","验证码输入错误");
            //跳转到登录页面 转发、重定向
            req.getRequestDispatcher("/login.jsp")
                    .forward(req,resp);
        }else{
            User user =
                    FormToBean.formToBean(User.class, req.getParameterMap());
            boolean loginOk = userService.login(user);

            if(loginOk){
                //登录成功，跳转到系统首页
                //存储用户信息，使用session存储
                session.setAttribute("user",user);
                //重定向到系统首页
                resp.sendRedirect("/employee/index.jsp");
            }else{
                //登录不成功
                req.setAttribute("msg","用户名或密码输入错误");
                //跳转到登录页面 转发、重定向
                req.getRequestDispatcher("/login.jsp")
                        .forward(req,resp);
            }
        }
    }

    public void register(HttpServletRequest req,
                      HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    public void logOut(HttpServletRequest req,
                      HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
