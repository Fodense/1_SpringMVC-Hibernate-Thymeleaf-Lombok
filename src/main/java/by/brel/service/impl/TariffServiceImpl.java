package by.brel.service.impl;

import by.brel.aspect.LogExecutionTime;
import by.brel.dao.TariffDAO;
import by.brel.entity.Tariff;
import by.brel.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TariffServiceImpl implements TariffService {

    private final TariffDAO tariffDAO;

    @Autowired
    public TariffServiceImpl(TariffDAO tariffDAO) {
        this.tariffDAO = tariffDAO;
    }

    @Override
    @Transactional
    @LogExecutionTime
    public List<Tariff> getAllTariffs() {
        return tariffDAO.getAllTariffs();
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "tariffs")
    @LogExecutionTime
    public List<Tariff> getAllTariffs(int page) {
        return tariffDAO.getAllTariffs(page);
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "tariff", key = "#id")
    @LogExecutionTime
    public Tariff findTariffById(long id) {
        return tariffDAO.findTariffById(id);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "tariffs", allEntries = true)
    @LogExecutionTime
    public void saveTariff(Tariff tariff) {
        tariffDAO.saveTariff(tariff);
    }

    @Override
    @Transactional
    @Caching(evict = {
            @CacheEvict(cacheNames = "tariff", key = "#id"),
            @CacheEvict(cacheNames = "tariffs", allEntries = true)
    })
    @LogExecutionTime
    public void deleteTariff(long id) {
        tariffDAO.deleteTariff(id);
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "tariffs")
    @LogExecutionTime
    public int getCountAllTariffs() {
        return tariffDAO.getCountAllTariffs();
    }
}
