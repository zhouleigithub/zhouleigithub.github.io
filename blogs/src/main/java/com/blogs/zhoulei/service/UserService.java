package com.blogs.zhoulei.service;

import com.blogs.zhoulei.po.User;

public interface UserService {
    User checkUser(String username,String password);
}
