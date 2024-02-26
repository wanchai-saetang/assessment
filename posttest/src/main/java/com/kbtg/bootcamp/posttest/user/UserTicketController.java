package com.kbtg.bootcamp.posttest.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserTicketController {
    private UserTicketService userTicketService;

    public UserTicketController(UserTicketService userTicketService) {
        this.userTicketService = userTicketService;
    }

    @PostMapping("/{userId}/lotteries/{ticketId}")
    public BuyTicketResponse buyTicket(@PathVariable String userId, @PathVariable String ticketId) {
        return this.userTicketService.buyTicket(userId, ticketId);
    }

}