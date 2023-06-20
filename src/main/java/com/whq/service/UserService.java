package com.whq.service;

import com.whq.domain.User;
import com.whq.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 罗长久
 * @version 1.0
 */
@Service
public class UserService {
    @Autowired
    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<User> findAll() {
        List<User> all = userMapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }

        return all;
    }

    public List<User> findByName(String keywords) {
        List<User> all = userMapper.findByName(keywords);
        for (User user : all) {
            System.out.println(user);
        }

        return all;
    }

    public User findById(Long id) {
        return userMapper.findById(id);
    }

    public void save(User user) {
        userMapper.save(user);
    }

    public void update(User user) {
        userMapper.update(user);
    }

    public void deleteById(Long id) {
        userMapper.deleteById(id);
    }


}
