package by.brel.dao;

import by.brel.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    List<Customer> getAllCustomers();

    List<Customer> getAllCustomers(int page);

    Customer findCustomerById(long id);

    void saveCustomer(Customer customer);

    void deleteCustomer(long id);

    long getCountAllCustomers();
}
