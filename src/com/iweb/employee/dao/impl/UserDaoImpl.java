package com.iweb.employee.dao.impl;

import com.iweb.employee.dao.UserDao;
import com.iweb.employee.pojo.User;
import com.iweb.employee.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    
    private QueryRunner qr = 
            new QueryRunner(DruidUtil.getDataSource());
    
    @Override
    public boolean login(User user) {
        String sql = "select count(*) from t_user where " +
                "username=? and password=?";
        try {
            Long count = qr.query(sql, new ScalarHandler<>(), user.getUsername()
                    , user.getPassword());
            //count.intValue():把包装类型转化成基本类型
            return count.intValue() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
