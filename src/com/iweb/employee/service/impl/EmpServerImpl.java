package com.iweb.employee.service.impl;

import com.iweb.employee.dao.EmpDao;
import com.iweb.employee.dao.impl.EmpDaoImpl;
import com.iweb.employee.pojo.Dept;
import com.iweb.employee.pojo.Emp;
import com.iweb.employee.service.EmpService;

import java.util.List;

public class EmpServerImpl implements EmpService {
    private EmpDao empDao=new EmpDaoImpl();
    @Override
    public List<Emp> empList(int page,int pageSize,String ename){
        return empDao.empList(page,pageSize,ename);
    }
    @Override
    public int count(String ename){
        return empDao.count(ename);
    }
    @Override
    public List<String> queryJobs() {
        return empDao.queryJobs();
    }

    @Override
    public List<Dept> queryDepts() {
        return empDao.queryDepts();
    }

    @Override
    public boolean addEmp(Emp emp) {
        return empDao.addEmp(emp);
    }
}
