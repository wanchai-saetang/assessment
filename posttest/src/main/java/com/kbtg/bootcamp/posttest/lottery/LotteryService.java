package com.kbtg.bootcamp.posttest.lottery;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    public List<Lottery> getLotteries() {
        List<Lottery> lotteries = lotteryRepository.findAll();
        return lotteries;
    }

    public LotteryResponse addLottery(LotteryRequest lotteryRequest) {
        Lottery lottery = new Lottery();
        lottery.setTicket(lotteryRequest.getTicket());
        lottery.setPrice(lotteryRequest.getPrice());
        lottery.setStock(lotteryRequest.getAmount());
        // TODO Update value if lottery exists and update only updateDateTime
        lottery.setCreatedDateTime(LocalDateTime.now());
        lottery.setUpdatedDateTime(LocalDateTime.now());
        lotteryRepository.save(lottery);

        LotteryResponse lotteryResponse = new LotteryResponse();
        lotteryResponse.setTicket(lottery.getTicket());
        return lotteryResponse;
    }
}
