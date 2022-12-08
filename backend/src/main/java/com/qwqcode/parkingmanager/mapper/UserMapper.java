package com.qwqcode.parkingmanager.mapper;

import com.qwqcode.parkingmanager.entity.User;
import com.qwqcode.parkingmanager.entity.VipCard;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("SELECT * FROM users WHERE phone=#{phone} LIMIT 1")
    User getUserByPhone(@Param("phone") String phone);

    @Select("SELECT * FROM users WHERE id=#{id} LIMIT 1")
    User getUserByID(@Param("id") int id);

    @Insert("INSERT INTO `users` (`phone`, `password`, `openid`, `birthday`, `gender`, `credit`, `amount`, `role`, `status`) VALUES (#{user.phone}, #{user.password}, #{user.openid}, #{user.birthday}, #{user.gender}, #{user.credit}, #{user.amount}, #{user.role}, #{user.status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createUser(@Param("user") User user);

    @Insert("INSERT INTO `vip_cards` (`expire`, `type`, `user_id`) VALUES (#{vip_card.expire}, #{vip_card.type}, #{vip_card.user_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createVipCard(@Param("vip_card") VipCard vipCard);

    @Select("SELECT * FROM `vip_cards` WHERE `user_id` = #{user_id}")
    VipCard findVipCard(@Param("user_id") int user_id);
}
