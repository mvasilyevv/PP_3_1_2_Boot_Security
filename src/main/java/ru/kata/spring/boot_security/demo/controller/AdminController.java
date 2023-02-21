package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.UserDetailsImpl;
import ru.kata.spring.boot_security.demo.service.role.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.user.UserService;

import java.util.Set;
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

    @GetMapping
    public String index (Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        model.addAttribute("person", userDetails.getUser());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("emptyUser", new User());
        return "admin/index";
    }

    @PatchMapping("/user/{id}/edit")
    public String updateUser(@PathVariable("id") long id, @ModelAttribute User user, @RequestParam("newPassword") String newPassword) {
        Set<Role> roles = user.getRoles().stream().map(role -> roleService.findByRoleTitle(role.getAuthority())).collect(Collectors.toSet());
        user.setRoles(roles);
        System.out.println(user);
        if (newPassword != null)
            user.setPassword(newPassword);
        userService.update(user,id);
        return "redirect:/admin";
    }

    @PostMapping("/user/new")
    public String addNewUser(@ModelAttribute("user") User newUser) {
        Set<Role> roles = newUser.getRoles().stream().map(role -> roleService.findByRoleTitle(role.getRole())).collect(Collectors.toSet());
        newUser.setRoles(roles);
        userService.save(newUser);
        return "redirect:/admin";
    }

    @DeleteMapping("/user/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
