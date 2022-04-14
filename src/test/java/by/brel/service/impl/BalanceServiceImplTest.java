package by.brel.service.impl;

import by.brel.dao.BalanceDAO;
import by.brel.entity.Balance;
import by.brel.entity.Customer;
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
class BalanceServiceImplTest {

    @Mock
    private BalanceDAO balanceDAO;

    @InjectMocks
    private BalanceServiceImpl balanceServiceImpl;

    @Test
    void getAllBalances() {
        List<Balance> balances = new ArrayList<>();
        balances.add(new Balance());
        balances.add(new Balance());

        when(balanceDAO.getAllBalances()).thenReturn(balances);

        List<Balance> balanceList = balanceServiceImpl.getAllBalances();

        assertEquals(2, balanceList.size());

        verify(balanceDAO, times(1)).getAllBalances();
    }

    @Test
    void testGetAllBalances() {
        List<Balance> balances = new ArrayList<>();
        balances.add(new Balance());
        balances.add(new Balance());

        when(balanceDAO.getAllBalances(1)).thenReturn(balances);

        List<Balance> balanceList = balanceServiceImpl.getAllBalances(1);

        assertEquals(2, balanceList.size());

        verify(balanceDAO, times(1)).getAllBalances(1);
    }

    @Test
    void findBalanceById() {
        when(balanceDAO.findBalanceById(1L)).thenReturn(new Balance());

        Balance balance = balanceServiceImpl.findBalanceById(1L);

        assertNotEquals(1L, balance.getIdBalance());
        assertNotEquals(new Customer(), balance.getCustomer());
        assertNotEquals(new Tariff(), balance.getTariff());
        assertNotEquals(100, balance.getBalance());

        verify(balanceDAO, times(1)).findBalanceById(1L);
    }

    @Test
    void saveBalance() {
        Balance balance = new Balance();

        balanceServiceImpl.saveBalance(balance);

        verify(balanceDAO, times(1)).saveBalance(balance);
    }

    @Test
    void deleteBalance() {
        balanceServiceImpl.deleteBalance(1L);

        verify(balanceDAO, times(1)).deleteBalance(1L);
    }

    @Test
    void getCountAllBalances() {
        when(balanceDAO.getCountAllBalances()).thenReturn(2);

        int countBalances = balanceServiceImpl.getCountAllBalances();

        assertTrue(countBalances > 0);

        verify(balanceDAO, times(1)).getCountAllBalances();
    }
}