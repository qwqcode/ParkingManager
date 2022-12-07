package com.qwqcode.parkingmanager.mapper;

import com.qwqcode.parkingmanager.entity.Car;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CarMapper {
    @Select("SELECT * FROM `cars` WHERE plate = #{plate} LIMIT 1")
    Car findCarByInfo(@Param("plate") String plate);

    @Select("SELECT * FROM `cars` WHERE user_id = #{user_id} LIMIT 1")
    Car findCarByUserID(@Param("user_id") int user_id);

    @Select("SELECT * FROM `cars` WHERE id = #{id} LIMIT 1")
    Car findCarByID(@Param("id") int id);

    @Insert("INSERT INTO `cars` (`plate`, `plate_type`, `user_id`) VALUES (#{car.plate}, #{car.plate_type}, #{car.user_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createCar(@Param("car") Car car);
}
