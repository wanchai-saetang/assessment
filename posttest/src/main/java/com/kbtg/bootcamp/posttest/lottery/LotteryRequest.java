package com.kbtg.bootcamp.posttest.lottery;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LotteryRequest {
    @NotNull
    @Size(min = 6, max = 6, message = "Ticket must be 6 characters")
    private String ticket;
    @NotNull
    @Min(value = 0, message="The price must be positive")
    private Integer price;
    @NotNull
    @Min(value = 1, message="The amount must be at least 1")
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
