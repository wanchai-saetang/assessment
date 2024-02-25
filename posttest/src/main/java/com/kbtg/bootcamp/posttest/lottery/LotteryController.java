package com.kbtg.bootcamp.posttest.lottery;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
public class LotteryController {
    private final LotteryService lotteryService;

    public LotteryController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    @PostMapping("/admin/lotteries")
    public LotteryResponse addLottery(@Valid @RequestBody LotteryRequest lotteryRequest) {
        return this.lotteryService.addLottery(lotteryRequest);
    }

    @GetMapping("/lotteries")
    public LotteriesResponse getLotteries() {
        return this.lotteryService.getLotteries();
    }
}
