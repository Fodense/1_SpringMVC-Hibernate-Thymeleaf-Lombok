package by.brel.dao.impl;

import by.brel.dao.CustomerDAO;
import by.brel.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getAllCustomers() {
        Session session = sessionFactory.getCurrentSession();

        List<Customer> customerList = session.createQuery("from Customer").getResultList();

        return customerList;
    }

    @Override
    public List<Customer> getAllCustomers(int page) {
        Session session = sessionFactory.getCurrentSession();

        List<Customer> customerList = session
                .createQuery("from Customer", Customer.class)
                .setFirstResult(10 * (page - 1))
                .setMaxResults(10)
                .list();

        return customerList;
    }

    @Override
    public Customer findCustomerById(long id) {
        Session session = sessionFactory.getCurrentSession();

        Customer customer = session.get(Customer.class, id);

        return customer;
    }

    @Override
    public void saveCustomer(Customer customer) {
        System.out.println(customer);

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(customer);
    }

    @Override
    public void deleteCustomer(long id) {
        Session session = sessionFactory.getCurrentSession();

        Query<Customer> query = session.createQuery("delete from Customer where idCustomer = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public long getCountAllCustomers() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select count(*) from Customer", Long.class).getSingleResult().intValue();
    }
}
