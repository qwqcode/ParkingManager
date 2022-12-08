package com.qwqcode.parkingmanager.mapper;

import com.qwqcode.parkingmanager.entity.Rec;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecMapper {
    @Insert("INSERT INTO `recs` (`car_id`, `park_id`, `in_at`, `out_at`) VALUES (#{rec.car_id}, #{rec.park_id}, #{rec.in_at}, #{rec.out_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createRec(@Param("rec") Rec rec);

    @Update("UPDATE `recs` SET `out_at` = NOW() WHERE `id` = #{rec_id}")
    int updateRecOutTime(@Param("rec_id") int rec_id);

    @Select("SELECT * FROM `recs` WHERE `car_id` = #{car_id} AND `out_at` IS NULL ORDER BY `created_at` DESC LIMIT 1")
    Rec findLatestRecByCarID(@Param("car_id") int car_id);

    @Select("SELECT * FROM `recs` LIMIT #{offset},#{limit} ORDER BY `created_at` DESC")
    List<Rec> findAllRecs(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT COUNT(*) FROM `recs`")
    int countAllRecs();

    @Select("SELECT * FROM `recs` WHERE `user_id` = #{user_id} LIMIT #{offset},#{limit} ORDER BY `created_at` DESC")
    List<Rec> findUserRecs(@Param("user_id") int user_id, @Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT COUNT(*) FROM `recs` WHERE `user_id` = #{user_id}")
    int countUserRecs(@Param("user_id") int user_id);
}
