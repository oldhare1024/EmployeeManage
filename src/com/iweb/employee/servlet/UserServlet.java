package com.iweb.employee.servlet;

import cn.hutool.json.JSONUtil;
import com.iweb.employee.pojo.User;
import com.iweb.employee.service.UserService;
import com.iweb.employee.service.impl.UserServiceImpl;
import com.iweb.employee.util.FormToBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user")
@MultipartConfig
public class UserServlet extends BaseServlet{

    private UserService userService = new UserServiceImpl();
    public User login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            User user = FormToBean.formToBean(User.class, req.getParameterMap());
            user= userService.login(user);
            if(user!=null){
                //登录成功，跳转到系统首页
                //存储用户信息，使用session存储
                session.setAttribute("user",user);
                /*设置session有效期为3天
                if(req.getParameter("save").equals("true")) {
                    session.setMaxInactiveInterval(60 * 60 * 24 * 1);
                }*/
                //重定向到系统首页
                resp.sendRedirect("/employee/index.jsp");
                return user;
            }else{
                //登录不成功
                req.setAttribute("msg","用户名或密码输入错误");
                //跳转到登录页面 转发、重定向
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }
        return null;
    }
    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usernm =req.getParameter("newname");
        String passwd =req.getParameter("newpwd");
        User user=new User();
        user.setUsername(usernm);
        user.setPassword(passwd);
        boolean registOk=userService.regist(user);
        if(registOk){
            resp.sendRedirect("/employee/index.jsp");
        }else {
            req.setAttribute("msg","注册失败，用户名已被占用");
            req.getRequestDispatcher("/login.jsp")
                    .forward(req,resp);
        }
    }
    public void logOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        session.removeAttribute("user");
        session.invalidate();
        resp.sendRedirect("/employee/login.jsp");
    }
    public void verifyOldPwd(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String passwrod=req.getParameter("password");
        User user=(User) req.getSession().getAttribute("user");
        if(!user.getPassword().equals(passwrod)){
            resp.getWriter().write("原始密码输入不正确");
        }
    }
    public void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newpass =req.getParameter("newpass");
        User user=(User) req.getSession().getAttribute("user");
        user.setPassword(newpass);
        boolean updateOk=userService.updatePwd(user);
        if(updateOk){
            resp.sendRedirect("/employee/login.jsp");
        }
    }
    public void upload(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        Part part=req.getPart("file");
        Long filesize=part.getSize();
        String fileName=part.getSubmittedFileName();
        fileName=System.currentTimeMillis()+fileName;
        String realPath=req.getServletContext().getRealPath("/upload");
        File file=new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }
        part.write(realPath+File.separator+fileName);
        Map<String,String> map=new HashMap<>();
        map.put("code","1");
        map.put("msg","上传文件成功");
        map.put("src","/employee/upload/"+fileName);
        String json=JSONUtil.toJsonStr(map);
        resp.getWriter().write(json);
    }
}
