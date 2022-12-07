package com.qwqcode.parkingmanager.mapper;

import com.qwqcode.parkingmanager.entity.Rec;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RecMapper {
    @Insert("INSERT INTO `recs` (`car_id`, `park_id`, `in_at`, `out_at`) VALUES (#{rec.car_id}, #{rec.park_id}, #{rec.in_at}, #{rec.out_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createRec(@Param("rec") Rec rec);

    @Update("UPDATE `recs` SET `out_at` = NOW() WHERE `id` = #{rec_id}")
    int updateRecOutTime(@Param("rec_id") int rec_id);
}
