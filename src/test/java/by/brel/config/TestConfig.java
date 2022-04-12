package by.brel.config;

import by.brel.dao.BalanceDAO;
import by.brel.dao.CustomerDAO;
import by.brel.dao.TariffDAO;
import by.brel.service.BalanceService;
import by.brel.service.CustomerService;
import by.brel.service.TariffService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public CustomerService customerService() {
        return Mockito.mock(CustomerService.class);
    }

    @Bean
    public TariffService tariffService() {
        return Mockito.mock(TariffService.class);
    }

    @Bean
    public BalanceService balanceService() {
        return Mockito.mock(BalanceService.class);
    }

    @Bean
    public CustomerDAO customerDAO() {
        return Mockito.mock(CustomerDAO.class);
    }

    @Bean
    public TariffDAO tariffDAO() {
        return Mockito.mock(TariffDAO.class);
    }

    @Bean
    public BalanceDAO balanceDAO() {
        return Mockito.mock(BalanceDAO.class);
    }

}
