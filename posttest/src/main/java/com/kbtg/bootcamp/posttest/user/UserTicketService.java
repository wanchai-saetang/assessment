package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserTicketService {

    private final UserTicketRepository userTicketRepository;
    private final LotteryRepository lotteryRepository;


    public UserTicketService(UserTicketRepository userTicketRepository, LotteryRepository lotteryRepository) {
        this.userTicketRepository = userTicketRepository;
        this.lotteryRepository = lotteryRepository;
    }

    public BuyTicketResponse buyTicket(String userId, String ticketId) {
        Lottery lottery = lotteryRepository.findByTicket(ticketId);
        if (lottery == null) {
            throw new NotFoundException(String.format("Lottery %s not found", ticketId));
        }

        UserTicket userTicket = new UserTicket();
        userTicket.setTicket(lottery);
        userTicket.setUserId(userId);
        userTicket.setCreatedDateTime(LocalDateTime.now());
        userTicket.setUpdatedDateTime(LocalDateTime.now());
        UserTicket userTicketSaved = userTicketRepository.save(userTicket);

        BuyTicketResponse buyTicketResponse = new BuyTicketResponse();
        buyTicketResponse.setId(userTicketSaved.getId());
        return buyTicketResponse;
    }
}
