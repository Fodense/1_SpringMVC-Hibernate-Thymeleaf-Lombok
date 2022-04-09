package by.brel.service.impl;

import by.brel.dao.TariffDAO;
import by.brel.entity.Tariff;
import by.brel.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Tariff> getAllTariffs() {
        return tariffDAO.getAllTariffs();
    }

    @Override
    @Transactional
    public List<Tariff> getAllTariffs(int page) {
        return tariffDAO.getAllTariffs(page);
    }

    @Override
    @Transactional
    public Tariff findTariffById(long id) {
        return tariffDAO.findTariffById(id);
    }

    @Override
    @Transactional
    public void saveTariff(Tariff tariff) {
        tariffDAO.saveTariff(tariff);
    }

    @Override
    @Transactional
    public void deleteTariff(long id) {
        tariffDAO.deleteTariff(id);
    }

    @Override
    @Transactional
    public int getCountAllTariffs() {
        return tariffDAO.getCountAllTariffs();
    }
}
