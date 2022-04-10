package by.brel.service;

import by.brel.entity.Tariff;

import java.util.List;

public interface TariffService {
    List<Tariff> getAllTariffs();

    List<Tariff> getAllTariffs(int page);

    Tariff findTariffById(long id);

    void saveTariff(Tariff tariff);

    void deleteTariff(long id);

    int getCountAllTariffs();
}
