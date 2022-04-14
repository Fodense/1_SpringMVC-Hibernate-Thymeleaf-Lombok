package by.brel.controller;

import by.brel.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CustomerControllerApiTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = MockMvcBuilders.standaloneSetup(this.customerController).build();
    }

    @Test
    void getAllCustomers() throws Exception {
        mockMvc.perform(get("/customers/main"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customers/mainCustomers"));
    }

    @Test
    void getCustomerById() throws Exception {
        mockMvc.perform(get("/customers/main/{id}", 2L))
                .andExpect(status().isOk())
                .andExpect(view().name("/customers/newOrUpdateCustomers"));
    }

    @Test
    void showNewOrUpdateCustomersView() throws Exception {
        mockMvc.perform(get("/customers/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customers/newOrUpdateCustomers"));
    }

    @Test
    void saveCustomer() throws Exception {
        mockMvc.perform(post("/customers/save")
                        .param("idCustomer", String.valueOf(1L))
                        .param("firstName", "Test")
                        .param("lastName", "Test")
                        .param("dateBirth", "1990-09-09")
                        .param("mobilePhone", "+375291111111"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("/customers/main"));
    }

    @Test
    void updateCustomer() throws Exception {
        mockMvc.perform(get("/customers/update/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("/customers/newOrUpdateCustomers"));
    }

    @Test
    void deleteCustomer() throws Exception {
        mockMvc.perform(get("/customers/delete/{id}", 1L))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("/customers/main"));
    }
}