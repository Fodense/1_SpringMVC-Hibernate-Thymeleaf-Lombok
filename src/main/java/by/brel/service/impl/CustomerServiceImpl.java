package by.brel.service.impl;

import by.brel.dao.CustomerDAO;
import by.brel.entity.Customer;
import by.brel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    @Transactional
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "customers")
    public List<Customer> getAllCustomers(int page) {
        return customerDAO.getAllCustomers(page);
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "customer", key = "#id")
    public Customer findCustomerById(long id) {
        return customerDAO.findCustomerById(id);
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "customers", allEntries = true)
    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    @Override
    @Transactional
    @Caching(evict = {
                    @CacheEvict(cacheNames = "customer", key = "#id"),
                    @CacheEvict(cacheNames = "customers", allEntries = true)
    })
    public void deleteCustomer(long id) {
        customerDAO.deleteCustomer(id);
    }

    @Override
    @Transactional
    @Cacheable(cacheNames = "customers")
    public int getCountAllCustomers() {
        return customerDAO.getCountAllCustomers();
    }
}
