package com.iweb.employee.dao.impl;

import com.iweb.employee.dao.UserDao;
import com.iweb.employee.pojo.User;
import com.iweb.employee.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    
    private QueryRunner qr = new QueryRunner(DruidUtil.getDataSource());
    
    @Override
    public User login(User user) {
        try {
            String sql = "select * from t_user where " +
                    "username=? and password=?";
            user = qr.query(sql, new BeanHandler<>(User.class), user.getUsername()
                    , user.getPassword());
            //count.intValue():把包装类型转化成基本类型
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean regist(User user) {
        try {
            String sql = "insert into t_user values(0,?,?,null,null,null,null)";
            int insert = qr.update(sql,user.getUsername(),user.getPassword());
            return insert>0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePwd(User user){
        try {
            String sql = "update t_user set password= ? where username = ?";
            int update = qr.update(sql, user.getPassword(), user.getUsername());
            return update > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
