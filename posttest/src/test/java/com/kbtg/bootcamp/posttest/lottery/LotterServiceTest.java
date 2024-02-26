package com.kbtg.bootcamp.posttest.lottery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LotterServiceTest {
    LotteryService lotteryService;
    @Mock
    LotteryRepository lotteryRepository;

    @BeforeEach
    void setUp() {
        lotteryService = new LotteryService(lotteryRepository);
    }

    @Test
    @DisplayName("when call getLotteries should return all lotteries")
    void getAllLotteries() {
        Lottery lottery = new Lottery();
        lottery.setTicket("000001");
        when(lotteryRepository.findAll()).thenReturn(List.of(lottery));
        assertEquals(List.of("000001"), lotteryService.getLotteries().getTickets());
    }

    @Test
    @DisplayName("when call addLottery should return the ticket number that just added")
    void addLottery() {
        LotteryRequest lotteryRequest = new LotteryRequest();
        lotteryRequest.setTicket("000001");
        lotteryRequest.setAmount(1);
        lotteryRequest.setPrice(80);

        assertEquals("000001", lotteryService.addLottery(lotteryRequest).getTicket());
    }
}
