package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.security.UserDetailsImpl;
import ru.kata.spring.boot_security.demo.service.roleService.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.userService.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public AdminController(UserService userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/show")
    public String show (Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("user", userDetails.getUser());
        return "admin/show";
    }

    @GetMapping("/users")
    public String usersList (Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/usersList";
    }
    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PostMapping("/users")
    public String addNewUser(@ModelAttribute("user") User newUser) {
        Role role = roleService.findById(2);
        newUser.setRole(role);
        userService.save(newUser);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}")
    public String index(@PathVariable("id") long id, Model model) {
        model.addAttribute("user",userService.findById(id));
        return "admin/index";
    }

    @GetMapping("/users/{id}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleService.findAll());
        return "admin/edit";
    }

    @PatchMapping("/users/{id}")
    public String updateUser(@PathVariable("id") long id, @ModelAttribute("user") User user) {
        List<Role> roles = user.getRoles().stream().map(role -> roleService.findByRoleTitle(role.getAuthority())).collect(Collectors.toList());
        user.setRoles(roles);
        userService.update(user,id);
        return "redirect:/admin/users/" + id;
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
