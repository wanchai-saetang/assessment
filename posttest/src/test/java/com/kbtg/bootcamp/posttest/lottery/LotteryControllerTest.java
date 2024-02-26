package com.kbtg.bootcamp.posttest.lottery;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class LotteryControllerTest {
    MockMvc mockMvc;
    @Mock
    LotteryService lotteryService;

    @BeforeEach
    void setUp() {
        LotteryController lotteryController = new LotteryController(lotteryService);
        mockMvc = MockMvcBuilders.standaloneSetup(lotteryController).alwaysDo(print()).build();
    }

    @Test
    @DisplayName("when perform on GET: /lotteries should return all lotteries")
    void getAllLotteries() throws Exception {
        LotteriesResponse lotteriesResponse = new LotteriesResponse();
        lotteriesResponse.setTickets(List.of("000001", "000002"));

        when(lotteryService.getLotteries()).thenReturn(lotteriesResponse);

        mockMvc.perform(get("/lotteries")).andExpect(status().isOk()).andExpect(jsonPath("$.tickets", is(List.of("000001", "000002"))));
    }

    @Test
    @DisplayName("when perform on POST: /admin/lotteries should return ticket number that just added")
    void addLottery() throws Exception {
        LotteryRequest lotteryRequest = new LotteryRequest();
        lotteryRequest.setTicket("000001");
        lotteryRequest.setAmount(1);
        lotteryRequest.setPrice(80);
        ObjectMapper objectMapper = new ObjectMapper();

        LotteryResponse lotteryResponse = new LotteryResponse();
        lotteryResponse.setTicket("000001");
        when(lotteryService.addLottery(any())).thenReturn(lotteryResponse);

        mockMvc.perform(post("/admin/lotteries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(lotteryRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ticket", is("000001")));
    }

}
