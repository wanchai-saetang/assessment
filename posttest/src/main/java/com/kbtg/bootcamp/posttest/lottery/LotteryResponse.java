package com.kbtg.bootcamp.posttest.lottery;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LotteryResponse {
    @NotNull
    @Size(min = 6, max = 6, message = "Ticket must be 6 characters")
    private String ticket;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
