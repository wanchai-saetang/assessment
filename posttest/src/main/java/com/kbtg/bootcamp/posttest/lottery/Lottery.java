package com.kbtg.bootcamp.posttest.lottery;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "lottery")
public class Lottery {
    @Id
    @Column(name = "ticket", length = 6)
    @Size(min = 6, max = 6, message = "Ticket must be 6 characters")
    private String ticket;
    @Column(name = "price")
    @Min(value = 0, message = "The price must be positive")
    private Integer price;
    @Column(name = "stock")
    @Min(value = 0, message = "The stock must be positive")
    private Integer stock;
    @Column(name = "created_datetime", nullable = false)
    private LocalDateTime createdDateTime;
    @Column(name = "updated_datetime", nullable = false)
    private LocalDateTime updatedDateTime;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(LocalDateTime createdDate) {
        this.createdDateTime = createdDate;
    }

    public LocalDateTime getUpdatedDateTime() {
        return updatedDateTime;
    }

    public void setUpdatedDateTime(LocalDateTime updatedDate) {
        this.updatedDateTime = updatedDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}