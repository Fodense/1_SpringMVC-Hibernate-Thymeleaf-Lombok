package by.brel.service.impl;

import by.brel.aspect.LogExecutionTime;
import by.brel.dao.BalanceDAO;
import by.brel.entity.Balance;
import by.brel.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
    @LogExecutionTime
    public List<Balance> getAllBalances() {
        return balanceDAO.getAllBalances();
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "balances")
    @LogExecutionTime
    public List<Balance> getAllBalances(int page) {
        return balanceDAO.getAllBalances(page);
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "balance", key = "#id")
    @LogExecutionTime
    public Balance findBalanceById(long id) {
        return balanceDAO.findBalanceById(id);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "balances", allEntries = true)
    @LogExecutionTime
    public void saveBalance(Balance balance) {
        balanceDAO.saveBalance(balance);
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = "balance", key = "#id"),
            @CacheEvict(cacheNames = "balances", allEntries = true)
    })
    @LogExecutionTime
    public void deleteBalance(long id) {
        balanceDAO.deleteBalance(id);
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "balances")
    @LogExecutionTime
    public int getCountAllBalances() {
        return balanceDAO.getCountAllBalances();
    }
}
