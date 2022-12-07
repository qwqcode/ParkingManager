package com.qwqcode.parkingmanager.mapper;

import com.qwqcode.parkingmanager.entity.Park;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ParkMapper {
    @Select("SELECT * FROM `parks` WHERE id = #{id} LIMIT 1")
    Park findParkByID(@Param("id") int id);
}
