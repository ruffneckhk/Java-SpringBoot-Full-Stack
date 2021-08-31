package com.jorgejag.curso.controllers;

import com.jorgejag.curso.dao.UserDao;
import com.jorgejag.curso.models.User;
import com.jorgejag.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {

        User loggedUser = userDao.getUserByCredentials(user);

        if (loggedUser != null) {
            String tokenJwt = jwtUtil.create(String.valueOf(loggedUser.getId()), loggedUser.getEmail());
            return tokenJwt;
        } else {
            return "FAIL";
        }
    }
}
