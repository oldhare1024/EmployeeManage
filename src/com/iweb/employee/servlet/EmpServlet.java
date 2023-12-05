package com.iweb.employee.servlet;

import com.iweb.employee.pojo.Dept;
import com.iweb.employee.pojo.Emp;
import com.iweb.employee.service.EmpService;
import com.iweb.employee.service.impl.EmpServerImpl;
import com.iweb.employee.util.FormToBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/emp")
public class EmpServlet extends BaseServlet{
    private EmpService empService= new EmpServerImpl();

    int pageSize = 4;
    public void empList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ename=req.getParameter("ename");
        int count =empService.count(ename);
        int pages=count%pageSize==0?count/pageSize:count/pageSize+1;
        int page=req.getParameter("page")==null?1:Integer.parseInt(req.getParameter("page"));
        List<Emp> emps = empService.empList(page,pageSize,ename);
        req.setAttribute("emps",emps);
        req.setAttribute("pages",pages);
        req.setAttribute("pageNow",page);
        req.getRequestDispatcher("/employee.jsp").forward(req,resp);
    }
    public void toAddEmp(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
        List<String> jobs=empService.queryJobs();
        List<Dept> depts=empService.queryDepts();
        req.setAttribute("jobs",jobs);
        req.setAttribute("depts",depts);
        req.getRequestDispatcher("/addEmp.jsp").forward(req,resp);
    }
    public void addEmp(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        Emp emp=FormToBean.formToBean(Emp.class,req.getParameterMap());
        boolean addOk=empService.addEmp(emp);
        if(addOk){
            resp.sendRedirect("/employee/emp?method=empList");
        }
    }

}
