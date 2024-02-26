package com.kbtg.bootcamp.posttest.user;

import com.kbtg.bootcamp.posttest.lottery.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
    List<UserTicket> findByUserId(String userId);
    void deleteByUserIdAndTicket(String userId, Lottery ticket);
    List<UserTicket> findByUserIdAndTicket(String userId, Lottery ticket);
}
