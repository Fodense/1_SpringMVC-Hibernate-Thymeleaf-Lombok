package by.brel.service.impl;

import by.brel.dao.CustomerDAO;
import by.brel.entity.Customer;
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
class CustomerServiceImplTest {

    @Mock
    private CustomerDAO customerDAO;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Test
    void getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerDAO.getAllCustomers()).thenReturn(customers);

        List<Customer> customerList = customerServiceImpl.getAllCustomers();

        assertEquals(2, customerList.size());

        verify(customerDAO, times(1)).getAllCustomers();
    }

    @Test
    void testGetAllCustomersPage() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer());
        customers.add(new Customer());

        when(customerDAO.getAllCustomers(1)).thenReturn(customers);

        List<Customer> customerList = customerServiceImpl.getAllCustomers(1);

        assertEquals(2, customerList.size());

        verify(customerDAO, times(1)).getAllCustomers(1);
    }

    @Test
    void findCustomerById() {
        when(customerDAO.findCustomerById(1L)).thenReturn(new Customer());

        Customer customer = customerServiceImpl.findCustomerById(1L);

        assertNotEquals(1L, customer.getIdCustomer());
        assertNotEquals("Test", customer.getFirstName());
        assertNotEquals("Test", customer.getLastName());
        assertNotEquals("2000-11-01", customer.getDateBirth());
        assertNotEquals("+375291291242", customer.getMobilePhone());

        verify(customerDAO, times(1)).findCustomerById(1L);
    }

    @Test
    void saveCustomer() {
        Customer customer = new Customer();

        customerServiceImpl.saveCustomer(customer);

        verify(customerDAO, times(1)).saveCustomer(customer);
    }

    @Test
    void deleteCustomer() {
        customerServiceImpl.deleteCustomer(1L);

        verify(customerDAO, times(1)).deleteCustomer(1L);
    }

    @Test
    void getCountAllCustomers() {
        when(customerDAO.getCountAllCustomers()).thenReturn(2);

        int countCustomers = customerServiceImpl.getCountAllCustomers();

        assertTrue(countCustomers > 0);

        verify(customerDAO, times(1)).getCountAllCustomers();
    }
}