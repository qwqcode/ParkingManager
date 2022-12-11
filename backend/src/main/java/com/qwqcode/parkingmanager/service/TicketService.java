package com.qwqcode.parkingmanager.service;

import com.qwqcode.parkingmanager.entity.Ticket;
import com.qwqcode.parkingmanager.entity.TicketPreset;
import com.qwqcode.parkingmanager.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {
    @Autowired
    private TicketMapper ticketMapper;

    public Ticket findTicketByID(int id) {
        return ticketMapper.findTicketByID(id);
    }

    public Ticket findTicketByKey(String ticket_key) {
        return ticketMapper.findTicketByKey(ticket_key);
    }

    public TicketPreset findTicketPresetByID(int id) {
        return ticketMapper.findTicketPresetByID(id);
    }
}
