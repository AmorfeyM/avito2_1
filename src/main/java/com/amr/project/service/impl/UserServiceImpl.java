package com.amr.project.service.impl;


import com.amr.project.dao.abstracts.UserDao;
import com.amr.project.model.entity.User;
import com.amr.project.service.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ReadWriteServiceImpl<User,Long> implements UserService {
    private UserDao userDao;
    @Autowired
    public UserServiceImpl( UserDao userDao) {
        super(userDao);
    }
}