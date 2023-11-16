package com.iweb.employee.dao;

import com.iweb.employee.pojo.User;

public interface UserDao {
    User login(User user);
    boolean updatePwd(User user);
}
