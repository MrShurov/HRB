package services;

import pojos.Bet;

import java.util.List;

public interface IBetService extends IService<Bet> {
    List getAllByUserId(Long userId);
    String createBet(Bet bet);
}
