package by.brel.service.impl;

import by.brel.dao.UserDAO;
import by.brel.entity.Role;
import by.brel.entity.User;
import by.brel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public User findUserByName(String name) {
        User user = userDAO.findUserByName(name);

        return user;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setRoles(Collections.singletonList(new Role("ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        userDAO.saveUser(user);
    }
}
