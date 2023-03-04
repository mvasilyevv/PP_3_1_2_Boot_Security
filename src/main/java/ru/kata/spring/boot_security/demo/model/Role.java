package ru.kata.spring.boot_security.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long id;
    @Column
    private String role;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<User> user = new HashSet<>();

    public Role() {}

    public Role(String role) {
        this.role = role;
    }

    @JsonIgnore
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @JsonIgnore
    public Set<User> getUsers() {
        return user;
    }

    public void setUser(User user) {
        this.user.add(user);
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return String.valueOf(role);
    }

    @JsonIgnore
    @Override
    public String getAuthority() {
        return role;
    }
}
