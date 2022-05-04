package by.brel.dao;

import by.brel.entity.User;

public interface UserDAO {

    User findUserByName(String name);

    void saveUser(User user);
}
