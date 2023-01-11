package com.javarush.khmelov.lesson14.controller;

import com.javarush.khmelov.lesson14.entity.User;
import com.javarush.khmelov.lesson14.service.UserService;
import com.javarush.khmelov.lesson14.util.Go;
import com.javarush.khmelov.lesson14.util.Jsp;
import com.javarush.khmelov.lesson14.util.Key;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;

@SuppressWarnings("serial")
@WebServlet(name = "UsersServlet", value = Go.USERS)
public class UsersServlet extends HttpServlet {

    private final UserService userService = UserService.USER_SERVICE;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Collection<User> users = userService.getAll();
        request.setAttribute(Key.USERS,users);
        Jsp.forward(request, response, Key.USERS);
    }


    public void destroy() {
    }
}