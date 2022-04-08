package by.brel.service;

import by.brel.entity.Balance;

import java.util.List;

public interface BalanceService {
    List<Balance> getAllBalances();

    Balance findBalanceById(long id);

    void saveBalance(Balance balance);

    void deleteBalance(long id);
}
