package by.brel.controller;

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

class TariffControllerTest {

    @Mock
    private TariffService tariffService;

    @InjectMocks
    private TariffController tariffController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(this.tariffController).build();
    }

    @Test
    void getAllTariffs() throws Exception {
        mockMvc.perform(get("/tariffs/main"))
                .andExpect(status().isOk())
                .andExpect(view().name("/tariffs/mainTariffs"));
    }

    @Test
    void getTariffById() throws Exception {
        mockMvc.perform(get("/tariffs/main/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("/tariffs/newOrUpdateTariffs"));
    }

    @Test
    void showNewOrUpdateTariffsView() throws Exception {
        mockMvc.perform(get("/tariffs/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/tariffs/newOrUpdateTariffs"));
    }

    @Test
    void saveTariff() throws Exception {
        mockMvc.perform(post("/tariffs/save")
                        .param("idTariff", String.valueOf(1L))
                        .param("title", "Test")
                        .param("price", String.valueOf(120.0)))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("/tariffs/main"));
    }

    @Test
    void updateTariff() throws Exception {
        mockMvc.perform(get("/tariffs/update/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("/tariffs/newOrUpdateTariffs"));
    }

    @Test
    void deleteTariff() throws Exception {
        mockMvc.perform(get("/tariffs/delete/{id}", 1L))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("/tariffs/main"));
    }
}