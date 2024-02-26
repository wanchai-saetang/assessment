package com.kbtg.bootcamp.posttest.exception;

import com.kbtg.bootcamp.posttest.user.UserTicketController;
import com.kbtg.bootcamp.posttest.user.UserTicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ApiExceptionHandlerTest {
    MockMvc mockMvc;

    @Mock
    UserTicketService userTicketService;

    @BeforeEach
    void setUp() {
        UserTicketController userTicketController = new UserTicketController(userTicketService);
        mockMvc = MockMvcBuilders.standaloneSetup(userTicketController).alwaysDo(print()).setControllerAdvice(new ApiExceptionHandler()).build();
    }

    @Test
    @DisplayName("when perform any action on not existing resorce should return NotFound response")
    void checkReturnResponseNotFound() throws Exception {
        when(userTicketService.getMyLotteries(any())).thenThrow(NotFoundException.class);
        mockMvc.perform(get("/users/0123456789/lotteries")).andExpect(status().isNotFound());
    }
}
