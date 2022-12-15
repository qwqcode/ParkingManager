package com.qwqcode.parkingmanager.mapper;

import com.qwqcode.parkingmanager.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Select("SELECT * FROM `tickets` ORDER BY `created_at` DESC")
    List<Ticket> findAllTickets();

    @Select("SELECT * FROM `ticket_presets` ORDER BY `created_at` DESC")
    List<TicketPreset> findAllTicketPresets();

    @Insert("INSERT INTO `ticket_presets` (`title`, `cond`, `cond_val`, `act`, `act_val`, `is_available`) " +
            "VALUES (#{p.title}, #{p.cond}, #{p.cond_val}, #{p.act}, #{p.act_val}, 1)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createTicketPreset(@Param("p") TicketPreset p);

    @Insert("INSERT INTO `tickets` (`ticket_key`, `preset_id`, `park_id`, `is_available`) " +
            "VALUES (#{t.ticket_key}, #{t.preset_id}, #{t.park_id}, 1)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int createTicket(@Param("t") Ticket t);

    @Delete("DELETE FROM `tickets` WHERE `id` = #{id}")
    int delTicket(@Param("id") int ticket_id);
}
