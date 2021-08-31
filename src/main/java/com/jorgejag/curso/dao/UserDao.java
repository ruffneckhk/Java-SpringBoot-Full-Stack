package com.jorgejag.curso.dao;

import com.jorgejag.curso.models.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    void delete(long id);

    void registerUser(User user);

    User getUserByCredentials(User user);
}
