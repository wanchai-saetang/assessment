package com.kbtg.bootcamp.posttest.user;

import java.util.List;

public class MyLotteriesResponse {
    private List<String> tickets;
    private Integer count;
    private Integer cost;

    public List<String> getTickets() {
        return tickets;
    }

    public void setTickets(List<String> tickets) {
        this.tickets = tickets;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
