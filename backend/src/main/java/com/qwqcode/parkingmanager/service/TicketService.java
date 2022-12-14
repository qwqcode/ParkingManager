package com.qwqcode.parkingmanager.service;

import com.qwqcode.parkingmanager.entity.Ticket;
import com.qwqcode.parkingmanager.entity.TicketPreset;
import com.qwqcode.parkingmanager.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public boolean updateTicketAvailable(int id, boolean available) {
        return ticketMapper.updateTicketAvailable(id, available ? 1 : 0) != 0;
    }

    public List<Ticket> findAllTickets() {
        return ticketMapper.findAllTickets();
    }

    public List<TicketPreset> findAllTicketPresets() {
        return ticketMapper.findAllTicketPresets();
    }
}
