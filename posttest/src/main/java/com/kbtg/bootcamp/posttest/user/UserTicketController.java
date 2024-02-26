package com.kbtg.bootcamp.posttest.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserTicketController {
    private UserTicketService userTicketService;

    public UserTicketController(UserTicketService userTicketService) {
        this.userTicketService = userTicketService;
    }

    @PostMapping("/{userId}/lotteries/{ticketId}")
    public BuyTicketResponse buyTicket(@PathVariable @NotBlank @Size(min = 10, max = 10, message = "User ID must be 10 digits") String userId, @PathVariable @NotBlank @Size(min = 6, max = 6, message = "Ticket number must be 10 digits") String ticketId) {
        return this.userTicketService.buyTicket(userId, ticketId);
    }


    @GetMapping("/{userId}/lotteries")
    public MyLotteriesResponse getMyLotteries(@PathVariable @NotBlank @Size(min = 10, max = 10, message = "User ID must be 10 digits") String userId) {
        return this.userTicketService.getMyLotteries(userId);
    }


}