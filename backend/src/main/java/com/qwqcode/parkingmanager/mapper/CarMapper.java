package com.qwqcode.parkingmanager.mapper;

import com.qwqcode.parkingmanager.entity.Car;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Select("SELECT * FROM `cars` ORDER BY `created_at` DESC LIMIT #{offset},#{limit}")
    List<Car> findAllCars(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT COUNT(*) `cars`")
    int countAllCars();

    @Select("SELECT * FROM `cars` WHERE `user_id` = #{user_id} ORDER BY `created_at` DESC")
    List<Car> findUserCars(@Param("user_id") int user_id);

    @Update("UPDATE `cars` SET `user_id` = #{user_id} WHERE `id` = #{car_id}")
    int editCarUserID(@Param("car_id") int car_id, @Param("user_id") int user_id);
}
