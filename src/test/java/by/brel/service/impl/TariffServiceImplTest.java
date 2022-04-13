package by.brel.service.impl;

import by.brel.dao.TariffDAO;
import by.brel.entity.Tariff;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TariffServiceImplTest {

    @Mock
    private TariffDAO tariffDAO;

    @InjectMocks
    private TariffServiceImpl tariffServiceImpl;

    @Test
    void getAllTariffs() {
        List<Tariff> tariffs = new ArrayList<>();
        tariffs.add(new Tariff());
        tariffs.add(new Tariff());

        when(tariffDAO.getAllTariffs()).thenReturn(tariffs);

        List<Tariff> tariffList = tariffServiceImpl.getAllTariffs();

        assertEquals(2, tariffList.size());

        verify(tariffDAO, times(1)).getAllTariffs();
    }

    @Test
    void testGetAllTariffsPage() {
        List<Tariff> tariffs = new ArrayList<>();
        tariffs.add(new Tariff());
        tariffs.add(new Tariff());

        when(tariffDAO.getAllTariffs(1)).thenReturn(tariffs);

        List<Tariff> tariffList = tariffServiceImpl.getAllTariffs(1);

        assertEquals(2, tariffList.size());

        verify(tariffDAO, times(1)).getAllTariffs(1);
    }

    @Test
    void findTariffById() {
        when(tariffDAO.findTariffById(1L)).thenReturn(new Tariff());

        Tariff tariff = tariffServiceImpl.findTariffById(1L);

        assertNotEquals(1L, tariff.getIdTariff());
        assertNotEquals("Test", tariff.getTitle());
        assertNotEquals(100, tariff.getPrice());

        verify(tariffDAO, times(1)).findTariffById(1L);
    }

    @Test
    void saveTariff() {
        Tariff tariff = new Tariff();

        tariffServiceImpl.saveTariff(tariff);

        verify(tariffDAO, times(1)).saveTariff(tariff);
    }

    @Test
    void deleteTariff() {
        tariffServiceImpl.deleteTariff(1L);

        verify(tariffDAO, times(1)).deleteTariff(1L);
    }

    @Test
    void getCountAllTariffs() {
        when(tariffDAO.getCountAllTariffs()).thenReturn(2);

        int countTariffs = tariffServiceImpl.getCountAllTariffs();

        assertTrue(countTariffs > 0);

        verify(tariffDAO, times(1)).getCountAllTariffs();
    }
}