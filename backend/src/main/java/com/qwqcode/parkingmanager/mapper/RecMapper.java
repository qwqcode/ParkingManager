package com.qwqcode.parkingmanager.mapper;

import com.qwqcode.parkingmanager.entity.Rec;
import com.qwqcode.parkingmanager.entity.RecPay;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RecMapper {
    @Select("SELECT * FROM `recs` WHERE `id` = #{rec_id} LIMIT 1")
    Rec findRecByID(@Param("rec_id") int id);

    @Insert("INSERT INTO `recs` (`car_id`, `park_id`, `in_at`, `out_at`) " +
            "VALUES (#{rec.car_id}, #{rec.park_id}, #{rec.in_at}, #{rec.out_at})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createRec(@Param("rec") Rec rec);

    @Update("UPDATE `recs` SET `out_at` = NOW() WHERE `id` = #{rec_id}")
    int updateRecOutTime(@Param("rec_id") int rec_id);

    @Update("UPDATE `recs` SET `rec_pay_id` = #{rec_pay_id} WHERE `id` = #{rec_id}")
    int updateRecPayID(@Param("rec_id") int rec_id, @Param("rec_pay_id") int rec_pay_id);

    @Select("SELECT * FROM `recs` WHERE `car_id` = #{car_id} AND `out_at` IS NULL ORDER BY `created_at` DESC LIMIT 1")
    Rec findLatestRecByCarID(@Param("car_id") int car_id);

    @Select("SELECT * FROM `recs` ORDER BY `created_at` DESC LIMIT #{offset},#{limit}")
    List<Rec> findAllRecs(@Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT COUNT(*) FROM `recs`")
    int countAllRecs();

    @Select("SELECT * FROM `recs` WHERE `car_id` IN (#{car_ids}) ORDER BY `created_at` DESC LIMIT #{offset},#{limit}")
    List<Rec> findRecsByCarIDs(@Param("car_ids") int[] car_ids, @Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT * FROM `recs` WHERE `car_id` = #{car_id} ORDER BY `created_at` DESC LIMIT #{offset},#{limit}")
    List<Rec> findCarRecsPagination(@Param("car_id") int car_id, @Param("offset") int offset, @Param("limit") int limit);

    @Select("SELECT * FROM `recs` WHERE `car_id` = #{car_id} ORDER BY `created_at` DESC")
    List<Rec> findCarRecs(@Param("car_id") int car_id);

    @Select("SELECT COUNT(*) FROM `recs` WHERE `car_id` = #{car_id}")
    int countCarRecs(@Param("car_id") int car_id);

    @Insert("INSERT INTO `rec_pays` (`car_id`, `rec_id`, `park_id`, `price`, `use_ticket_id`, `use_coupon_id`, `is_use_vip_card`) " +
            "VALUES (#{p.car_id}, #{p.rec_id}, #{p.park_id}, #{p.price}, #{p.use_ticket_id}, #{p.use_coupon_id}, #{p.is_use_vip_card})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createRecPay(@Param("p") RecPay rec_pay);
}
