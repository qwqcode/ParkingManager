package com.qwqcode.parkingmanager.mapper;

import com.qwqcode.parkingmanager.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("SELECT * FROM users WHERE phone=#{phone} LIMIT 1")
    User getUserByPhone(@Param("phone") String phone);

    @Select("SELECT * FROM users WHERE id=#{id} LIMIT 1")
    User getUserByID(@Param("id") int id);

    @Insert("INSERT INTO `users` (`phone`, `password`, `openid`, `birthday`, `gender`, `credit`, `amount`, `role`, `status`) VALUES (#{user.phone}, #{user.password}, #{user.openid}, #{user.birthday}, #{user.gender}, #{user.credit}, #{user.amount}, #{user.role}, #{user.status});")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createUser(@Param("user") User user);
}
