package user;

import com.kbtg.bootcamp.posttest.lottery.LotteryResponse;
import com.kbtg.bootcamp.posttest.user.BuyTicketResponse;
import com.kbtg.bootcamp.posttest.user.MyLotteriesResponse;
import com.kbtg.bootcamp.posttest.user.UserTicketController;
import com.kbtg.bootcamp.posttest.user.UserTicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserTicketControllerTest {
    MockMvc mockMvc;

    @Mock
    UserTicketService userTicketService;

    @BeforeEach
    void setUp() {
        UserTicketController userTicketController = new UserTicketController(userTicketService);
        mockMvc = MockMvcBuilders.standaloneSetup(userTicketController).alwaysDo(print()).build();
    }

    @Test
    @DisplayName("when perform on POST: /users/{userId}/lotteries/{ticketId} should return user's lotteries")
    void userBuyTicket() throws Exception {
        BuyTicketResponse buyTicketResponse = new BuyTicketResponse();
        buyTicketResponse.setId(1L);
        when(userTicketService.buyTicket(any(), any())).thenReturn(buyTicketResponse);
        mockMvc.perform(post("/users/0123456789/lotteries/000001")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    @DisplayName("when perform on GET: /users/{userId}/lotteries")
    void userGetTicket() throws Exception {
        MyLotteriesResponse myLotteriesResponse = new MyLotteriesResponse();
        myLotteriesResponse.setCount(1);
        myLotteriesResponse.setCost(80);
        myLotteriesResponse.setTickets(List.of("000001"));

        when(userTicketService.getMyLotteries("0123456789")).thenReturn(myLotteriesResponse);

        mockMvc.perform(get("/users/0123456789/lotteries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tickets", is(List.of("000001"))))
                .andExpect(jsonPath("$.count", is(1)))
                .andExpect(jsonPath("$.cost", is(80)));
    }

    @Test
    @DisplayName("when perform on DELETE: /users/{userId}/lotteries/{ticketId}")
    void userSellTicket() throws Exception {
        LotteryResponse lotteryResponse = new LotteryResponse();
        lotteryResponse.setTicket("000001");

        when(userTicketService.sellMyTicket("0123456789", "000001")).thenReturn(lotteryResponse);

        mockMvc.perform(delete("/users/0123456789/lotteries/000001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticket", is("000001")));
    }
}
