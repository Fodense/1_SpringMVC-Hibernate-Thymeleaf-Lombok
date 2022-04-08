package by.brel.dao.impl;

import by.brel.dao.BalanceDAO;
import by.brel.entity.Balance;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BalanceDAOImpl implements BalanceDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BalanceDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Balance> getAllBalances() {
        Session session = sessionFactory.getCurrentSession();

        List<Balance> balances = session.createQuery("from Balance").getResultList();

        return balances;
    }

    @Override
    public Balance findBalanceById(long id) {
        Session session = sessionFactory.getCurrentSession();

        Balance balance = session.get(Balance.class, id);

        return balance;
    }

    @Override
    public void saveBalance(Balance balance) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(balance);
    }

    @Override
    public void deleteBalance(long id) {
        Session session = sessionFactory.getCurrentSession();

        Query<Balance> query = session.createQuery("delete from Balance where idBalance = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
