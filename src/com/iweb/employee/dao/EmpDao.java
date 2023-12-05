package com.iweb.employee.dao;

import com.iweb.employee.pojo.Dept;
import com.iweb.employee.pojo.Emp;

import java.util.List;

public interface EmpDao {
    List<Emp> empList(int page,int pageSize,String ename);
    int count(String ename);

    List<String> queryJobs();

    List<Dept> queryDepts();

    boolean addEmp(Emp emp);
}
