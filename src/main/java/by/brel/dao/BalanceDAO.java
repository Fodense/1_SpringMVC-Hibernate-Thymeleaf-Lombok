package by.brel.dao;

import by.brel.entity.Balance;

import java.util.List;

public interface BalanceDAO {
    List<Balance> getAllBalances();

    Balance findBalanceById(long id);

    void saveBalance(Balance balance);

    void deleteBalance(long id);
}
