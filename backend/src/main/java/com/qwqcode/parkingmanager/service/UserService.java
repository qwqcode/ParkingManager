package com.qwqcode.parkingmanager.service;

import com.qwqcode.parkingmanager.entity.User;
import com.qwqcode.parkingmanager.entity.VipCard;
import com.qwqcode.parkingmanager.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User checkGetUser(String phone, String password) {
        User u = userMapper.getUserByPhone(phone);
        if (u == null) return null;
        if (!u.getPassword().equals(password))
            return null;

        return u;
    }

    public User getUserByID(int id) {
        return userMapper.getUserByID(id);
    }

    public boolean signupUser(String phone, String password) {
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        return (userMapper.createUser(user) != 0);
    }

    public boolean createVipCard(VipCard vipCard) {
        return (userMapper.createVipCard(vipCard) != 0);
    }

    public VipCard findVipCard(int user_id) {
        return userMapper.findVipCard(user_id);
    }
}
