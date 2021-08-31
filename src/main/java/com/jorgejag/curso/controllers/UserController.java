package com.jorgejag.curso.controllers;

import com.jorgejag.curso.dao.UserDao;
import com.jorgejag.curso.models.User;
import com.jorgejag.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setName("Jorge Juan");
        user.setEmail("jorgejnet@gmail.com");
        user.setPhone("657401645");
        return user;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String token) {

        if (!validateToken(token)) {
            return null;
        }
        return userDao.getUsers();
    }

    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value = "Authorization") String token, @PathVariable long id) {
        if (!validateToken(token)) {
            return;
        }
        userDao.delete(id);
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        userDao.registerUser(user);
    }

    private boolean validateToken(String token) {
        String userId = jwtUtil.getKey(token);
        return userId != null;

    }

}
