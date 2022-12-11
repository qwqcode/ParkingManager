package com.qwqcode.parkingmanager.model.res;

import com.qwqcode.parkingmanager.entity.*;

public class UserTicketQueryResp {
    private Ticket ticket;
    private TicketPreset preset;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public TicketPreset getPreset() {
        return preset;
    }

    public void setPreset(TicketPreset preset) {
        this.preset = preset;
    }
}
