package by.brel.controller;

import by.brel.service.BalanceService;
import by.brel.service.CustomerService;
import by.brel.service.TariffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BalanceControllerTest {

    @Mock
    private BalanceService balanceService;

    @Mock
    private CustomerService customerService;

    @Mock
    private TariffService tariffService;

    @InjectMocks
    private BalanceController balanceController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(this.balanceController).build();
    }

    @Test
    void getAllBalances() throws Exception {
        mockMvc.perform(get("/balances/main"))
                .andExpect(status().isOk())
                .andExpect(view().name("/balances/mainBalances"));
    }

    @Test
    void getBalancesById() throws Exception {
        mockMvc.perform(get("/balances/main/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("/balances/newOrUpdateBalances"));
    }

    @Test
    void showNewOrUpdateBalancesView() throws Exception {
        mockMvc.perform(get("/balances/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/balances/newOrUpdateBalances"));
    }

    @Test
    void saveBalance() throws Exception {
        mockMvc.perform(post("/balances/save")
                        .param("idBalance", String.valueOf(1L))
                        .param("balance", String.valueOf(120.0)))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("/balances/main"));
    }

    @Test
    void updateBalance() throws Exception {
        mockMvc.perform(get("/balances/update/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("/balances/newOrUpdateBalances"));
    }

    @Test
    void deleteBalance() throws Exception {
        mockMvc.perform(get("/balances/delete/{id}", 1L))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("/balances/main"));
    }
}