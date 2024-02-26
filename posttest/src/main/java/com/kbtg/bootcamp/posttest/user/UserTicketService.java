package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


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

    public MyLotteriesResponse getMyLotteries(String userId) {
        List<UserTicket> userTickets = userTicketRepository.findByUserId(userId);
        if (userTickets.isEmpty()) {
            throw new NotFoundException(String.format("User ID %s or ticket of the User ID not found", userId));
        }
        MyLotteriesResponse myLotteriesResponse = new MyLotteriesResponse();
        List<Lottery> lotteries = userTickets.stream().map(UserTicket::getTicket).toList();
        myLotteriesResponse.setTickets(lotteries.stream().map(Lottery::getTicket).toList());
        myLotteriesResponse.setCount(userTickets.size());
        myLotteriesResponse.setCost(lotteries.stream().map(Lottery::getPrice).reduce(0,Integer::sum));

        return myLotteriesResponse;
    }
}
