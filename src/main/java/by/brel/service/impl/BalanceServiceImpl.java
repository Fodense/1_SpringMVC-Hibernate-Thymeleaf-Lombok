package by.brel.service.impl;

import by.brel.dao.BalanceDAO;
import by.brel.entity.Balance;
import by.brel.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceDAO balanceDAO;

    @Autowired
    public BalanceServiceImpl(BalanceDAO balanceDAO) {
        this.balanceDAO = balanceDAO;
    }

    @Override
    @Transactional
    public List<Balance> getAllBalances() {
        return balanceDAO.getAllBalances();
    }

    @Override
    @Transactional
    public Balance findBalanceById(long id) {
        return balanceDAO.findBalanceById(id);
    }

    @Override
    @Transactional
    public void saveBalance(Balance balance) {
        balanceDAO.saveBalance(balance);
    }

    @Override
    @Transactional
    public void deleteBalance(long id) {
        balanceDAO.deleteBalance(id);
    }
}
