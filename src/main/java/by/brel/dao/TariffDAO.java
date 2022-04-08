package by.brel.dao;

import by.brel.entity.Tariff;

import java.util.List;

public interface TariffDAO {
    List<Tariff> getAllTariffs();

    Tariff findTariffById(long id);

    void saveTariff(Tariff tariff);

    void deleteTariff(long id);
}
