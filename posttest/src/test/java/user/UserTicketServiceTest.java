package user;

import com.kbtg.bootcamp.posttest.exception.NotFoundException;
import com.kbtg.bootcamp.posttest.lottery.Lottery;
import com.kbtg.bootcamp.posttest.lottery.LotteryRepository;
import com.kbtg.bootcamp.posttest.lottery.LotteryResponse;
import com.kbtg.bootcamp.posttest.user.MyLotteriesResponse;
import com.kbtg.bootcamp.posttest.user.UserTicket;
import com.kbtg.bootcamp.posttest.user.UserTicketRepository;
import com.kbtg.bootcamp.posttest.user.UserTicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTicketServiceTest {
    @Mock
    UserTicketRepository userTicketRepository;

    @Mock
    LotteryRepository lotteryRepository;

    UserTicketService userTicketService;
    String userId;
    String ticket;

    @BeforeEach
    void setUp() {
        userTicketService = new UserTicketService(userTicketRepository, lotteryRepository);
        userId = "0123456789";
        ticket = "000001";
    }

    @Test
    @DisplayName("when call buyTicket return with id number of transaction")
    void buyTicketSuccess() {
        Lottery mockLottery = new Lottery();
        mockLottery.setTicket(ticket);
        when(lotteryRepository.findByTicket(ticket)).thenReturn(mockLottery);

        UserTicket mockUserTicket = new UserTicket();
        mockUserTicket.setUserId(userId);
        mockUserTicket.setId(1L);
        mockUserTicket.setTicket(mockLottery);
        when(userTicketRepository.save(any())).thenReturn(mockUserTicket);

        assertEquals(1L, userTicketService.buyTicket(userId, ticket).getId());
    }

    @Test
    @DisplayName("when call buyTicket with not exist ticket should got NotFoundException")
    void buyTicketNotFound() {
        assertThrows(NotFoundException.class, () -> userTicketService.buyTicket(userId, ticket));
    }

    @Test
    @DisplayName("when call getMyLotteries with userId should return the user tickets, count, and total cost")
    void getMyLotteriesSuccess() {
        UserTicket mockUserTicket = new UserTicket();
        mockUserTicket.setUserId(userId);
        Lottery mockLottery = new Lottery();
        mockLottery.setTicket(ticket);
        mockLottery.setPrice(80);
        mockUserTicket.setTicket(mockLottery);

        when(userTicketRepository.findByUserId(userId)).thenReturn(List.of(mockUserTicket));

        MyLotteriesResponse myLotteriesResponse = userTicketService.getMyLotteries(userId);
        assertEquals(ticket, myLotteriesResponse.getTickets().get(0));
        assertEquals(1, myLotteriesResponse.getCount());
        assertEquals(80, myLotteriesResponse.getCost());
    }

    @Test
    @DisplayName("when call getMyLotteries with wrong userId should got NotFoundException")
    void getMyLotteriesFailed() {
        assertThrows(NotFoundException.class, () -> userTicketService.getMyLotteries(userId));
    }

    @Test
    @DisplayName("when call sellMyTicket with userId and ticket number should got reponse with ticket was sold number")
    void sellMyTicketSuccess() {
        Lottery mockLottery = new Lottery();
        mockLottery.setTicket(ticket);
        when(lotteryRepository.findByTicket(ticket)).thenReturn(mockLottery);

        UserTicket mockUserTicket = new UserTicket();
        mockUserTicket.setTicket(mockLottery);
        mockUserTicket.setUserId(userId);
        when(userTicketRepository.findByUserIdAndTicket(userId, mockLottery)).thenReturn(List.of(mockUserTicket));

        LotteryResponse gotResponse = userTicketService.sellMyTicket(userId, ticket);
        assertEquals(ticket, gotResponse.getTicket());
    }

    @Test
    @DisplayName("when call sellMyTicket with wrong userId should got NotFoundException")
    void sellMyTicketFailedUserId() {
        Lottery mockLottery = new Lottery();
        mockLottery.setTicket(ticket);
        when(lotteryRepository.findByTicket(ticket)).thenReturn(mockLottery);

        assertThrows(NotFoundException.class, () -> userTicketService.sellMyTicket(userId, ticket));
    }

    @Test
    @DisplayName("when call sellMyTicket with wrong ticket should got NotFoundException")
    void sellMyTicketFailedTicket() {
        assertThrows(NotFoundException.class, () -> userTicketService.sellMyTicket(userId, ticket));
    }
}
