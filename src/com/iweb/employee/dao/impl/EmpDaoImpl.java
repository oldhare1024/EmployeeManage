package com.iweb.employee.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.iweb.employee.dao.EmpDao;
import com.iweb.employee.pojo.Dept;
import com.iweb.employee.pojo.Emp;
import com.iweb.employee.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDaoImpl implements EmpDao {
    private QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
    @Override
    public List<Emp> empList(int page, int pageSize, String ename) {
        List<Emp> emps=null;
        int a = (page - 1) * pageSize;
        String sql = "select e2.empno,e2.job,e2.sal,e2.hiredate,e2.deptno,e1.ename mgr,e2.ename ename from \n" +
                " (select * from emp) e1,\n" +
                " (select * from emp) e2\n" +
                " where e1.empno=e2.mgr";
        if (StrUtil.isNotEmpty(ename)) {
            sql = sql + " and e2.ename like ?";
        }
        sql=sql+" limit "+ a + "," +pageSize;
        try {
            if(StrUtil.isNotEmpty(ename)){
                emps=qr.query(sql,new BeanListHandler<>(Emp.class),"%"+ename+"%");
            }else {
                emps=qr.query(sql,new BeanListHandler<>(Emp.class));
            }
            return emps;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public int count(String ename) {
        String sql = "select count(*) from \n" +
                " (select * from emp) e1,\n" +
                " (select * from emp) e2\n" +
                " where e1.empno=e2.mgr";
        Long count = null;
        try {
            if (StrUtil.isNotEmpty(ename)) {
                sql = sql + " and e2.ename like ?";
                count = qr.query(sql, new ScalarHandler<>(), "%" + ename + "%");
            } else {
                count = qr.query(sql, new ScalarHandler<>());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count.intValue();
    }
    @Override
    public List<String> queryJobs(){
        String sql="select distinct job from emp";
        try {
            List<Emp> emps=qr.query(sql,new BeanListHandler<>(Emp.class));
            List<String> jobs=new ArrayList<>();
            for(Emp emp:emps){
                jobs.add(emp.getJob());
            }
            return jobs;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public List<Dept> queryDepts() {
        String sql="select * from dept";
        try {
            List<Dept> depts = qr.query(sql,new BeanListHandler<>(Dept.class));
            return depts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addEmp(Emp emp) {
        String sql="insert into emp(ename,job,sal,hiredate,deptno) values(?,?,?,?,?)";
        try {
            int update = qr.update(sql,emp.getEname(),emp.getJob(),emp.getSal(),emp.getHiredate(),emp.getDeptno());
            return update>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
