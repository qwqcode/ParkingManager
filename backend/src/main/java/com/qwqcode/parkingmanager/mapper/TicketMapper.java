package com.qwqcode.parkingmanager.mapper;

import com.qwqcode.parkingmanager.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TicketMapper {
    @Select("SELECT * FROM `tickets` WHERE `id` = #{id} LIMIT 1")
    Ticket findTicketByID(@Param("id") int id);

    @Select("SELECT * FROM `tickets` WHERE `ticket_key` = #{ticket_key} LIMIT 1")
    Ticket findTicketByKey(@Param("ticket_key") String ticket_key);

    @Select("SELECT * FROM `ticket_presets` WHERE `id` = #{id} LIMIT 1")
    TicketPreset findTicketPresetByID(@Param("id") int id);

    @Update("UPDATE `tickets` SET `is_available` = #{available} WHERE `id` = #{id}")
    int updateTicketAvailable(@Param("id") int id, @Param("available") int available);
}
