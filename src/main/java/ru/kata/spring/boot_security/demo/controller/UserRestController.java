package ru.kata.spring.boot_security.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dto.RoleDTO;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.UserDetailsImpl;
import ru.kata.spring.boot_security.demo.service.role.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.user.UserService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserRestController {
    private final UserService userService;
    private final RoleServiceImpl roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRestController(UserService userService, RoleServiceImpl roleService, ModelMapper modelMapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/users/current")
    public UserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return convertToUserDTO(userDetails.getUser());
    }

    @GetMapping("/roles")
    public List<RoleDTO> getRoles() {
        return roleService.findAll().stream().map(this::convertToRoleDTO).collect(Collectors.toList());
    }

    @GetMapping("/users")
    public List<UserDTO> getUsersList() {
        return userService.findAll().stream().map(this::convertToUserDTO).collect(Collectors.toList());
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> addNewUser(@RequestBody UserDTO newUserDTO) {
        Set<Role> roles = newUserDTO.getRoles().stream().map(role -> roleService.findByRoleTitle(role.getRole())).collect(Collectors.toSet());
        newUserDTO.setRoles(roles);
        userService.save(convertToUser(newUserDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public UserDTO getUserByID(@PathVariable("id") long id) {
        return convertToUserDTO(userService.findById(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<HttpStatus> updateUser(@PathVariable("id") long id, @RequestBody UserDTO updatedUserDTO) {
        Set<Role> roles = updatedUserDTO.getRoles().stream().map(role -> roleService.findByRoleTitle(role.getAuthority())).collect(Collectors.toSet());
        updatedUserDTO.setRoles(roles);
        if (!updatedUserDTO.getNewPassword().equals("")) {
            updatedUserDTO.setPassword(updatedUserDTO.getNewPassword());
        }
        userService.update(convertToUser(updatedUserDTO),id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private UserDTO convertToUserDTO (User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    private User convertToUser (UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
    private RoleDTO convertToRoleDTO (Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }
}
