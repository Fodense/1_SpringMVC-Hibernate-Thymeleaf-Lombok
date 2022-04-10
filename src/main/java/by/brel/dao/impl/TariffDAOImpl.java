package by.brel.dao.impl;

import by.brel.dao.TariffDAO;
import by.brel.entity.Tariff;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TariffDAOImpl implements TariffDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public TariffDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Tariff> getAllTariffs() {
        Session session = sessionFactory.getCurrentSession();

        List<Tariff> tariffs = session.createQuery("from Tariff", Tariff.class).getResultList();

        return tariffs;
    }

    @Override
    public List<Tariff> getAllTariffs(int page) {
        Session session = sessionFactory.getCurrentSession();

        List<Tariff> tariffList = session
                .createQuery("from Tariff", Tariff.class)
                .setFirstResult(10 * (page - 1))
                .setMaxResults(10)
                .list();

        return tariffList;
    }

    @Override
    public Tariff findTariffById(long id) {
        Session session = sessionFactory.getCurrentSession();

        Tariff tariff = session.get(Tariff.class, id);

        return tariff;
    }

    @Override
    public void saveTariff(Tariff tariff) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(tariff);
    }

    @Override
    public void deleteTariff(long id) {
        Session session = sessionFactory.getCurrentSession();

        Query<Tariff> query = session.createQuery("delete from Tariff where idTariff = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public int getCountAllTariffs() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("select count(*) from Tariff", Number.class).getSingleResult().intValue();
    }
}