package com.iweb.employee.service.impl;

import com.iweb.employee.dao.UserDao;
import com.iweb.employee.dao.impl.UserDaoImpl;
import com.iweb.employee.pojo.User;
import com.iweb.employee.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public User login(User user) {
        return userDao.login(user);
    }
    public boolean updatePwd(User user) {
        return userDao.updatePwd(user);
    }
}
