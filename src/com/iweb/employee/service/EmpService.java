package com.iweb.employee.service;

import com.iweb.employee.pojo.Dept;
import com.iweb.employee.pojo.Emp;

import java.util.List;

public interface EmpService {
    List<Emp> empList(int page,int pageSize,String ename);
    int count(String ename);
    List<String> queryJobs();
    List<Dept> queryDepts();

    boolean addEmp(Emp emp);
}
