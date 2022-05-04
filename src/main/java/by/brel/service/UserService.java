package by.brel.service;

import by.brel.entity.User;

public interface UserService {

    User findUserByName(String name);

    void saveUser(User user);
}
