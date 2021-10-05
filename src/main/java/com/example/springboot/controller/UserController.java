package com.example.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.springboot.model.User;
import com.example.springboot.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/admin")
    public String getAdminPage(Model model) {
        return "admin";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        return "login";
    }

    @GetMapping("/logout")
    public String getLogout(Model model) {
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String getUserPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user";
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("users", new User());
        return "info";
    }

    @GetMapping("/updateUser")
    public String updateUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "updateUser";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "deleteUser";
    }

    @PostMapping("/saveUser")
    public String createUser(@ModelAttribute("users") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") long id) {
        model.addAttribute("users", userService.getUserById(id));
        return "update";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("users") User user, @PathVariable("id") long id) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @PostMapping("/{id}/delete")
    public String delete(@ModelAttribute("id") long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
