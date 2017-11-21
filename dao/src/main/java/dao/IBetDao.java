package dao;

import pojos.Bet;

import java.util.List;

public interface IBetDao extends Dao<Bet> {
    List getAllByUserId(Long userId);
}
