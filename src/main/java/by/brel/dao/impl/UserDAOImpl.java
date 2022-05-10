package by.brel.dao.impl;

import by.brel.dao.UserDAO;
import by.brel.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User findUserByName(String name) {
        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, name);

        return user;
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();

        session.save(user);
    }
}
