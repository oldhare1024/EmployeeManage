package com.iweb.employee.service;

import com.iweb.employee.pojo.User;

public interface UserService {

    User login(User user);
    boolean updatePwd(User user);
}
