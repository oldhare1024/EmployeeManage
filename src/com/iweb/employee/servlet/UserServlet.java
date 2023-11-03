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
       //��ȡ�û��������֤��
        String code = req.getParameter("code");
        //��ȡ��ȷ����֤��
        HttpSession session = req.getSession();

        String correctCode = (String) session.getAttribute("code");
        if(!correctCode.equalsIgnoreCase(code)){
            //�û��������֤�벻��ȷ�����ü�����ʾ��Ϣ
            req.setAttribute("msg","��֤���������");
            //��ת����¼ҳ�� ת�����ض���
            req.getRequestDispatcher("/login.jsp")
                    .forward(req,resp);
        }else{
            User user =
                    FormToBean.formToBean(User.class, req.getParameterMap());
            boolean loginOk = userService.login(user);

            if(loginOk){
                //��¼�ɹ�����ת��ϵͳ��ҳ
                //�洢�û���Ϣ��ʹ��session�洢
                session.setAttribute("user",user);
                //�ض���ϵͳ��ҳ
                resp.sendRedirect("/employee/index.jsp");
            }else{
                //��¼���ɹ�
                req.setAttribute("msg","�û����������������");
                //��ת����¼ҳ�� ת�����ض���
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
