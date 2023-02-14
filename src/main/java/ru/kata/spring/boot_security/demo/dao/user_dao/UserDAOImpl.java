package ru.kata.spring.boot_security.demo.dao.user_dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private final Session session;
    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        session = entityManager.unwrap(Session.class);
    }

    @Override
    public List<User> findAll() {
        return session.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User findById(long id) {
        return session.get(User.class, id);
    }

    @Override
    public User findByUsername(String username) {
        Query<User> userQuery = session.createQuery("FROM User U WHERE U.username = :username", User.class);
        userQuery.setParameter("username", username);
        User user = null;
        try {
            user = userQuery.getSingleResult();
        } catch (NoResultException ignored) {}
        return user;
    }

    @Override
    public void save(User user) {
        session.persist(user);
    }

    @Override
    public void update(User updatedUser, long id) {
        User newUser = session.get(User.class, id);
        newUser.setId(updatedUser.getId());
        newUser.setUsername(updatedUser.getUsername());
        newUser.setPassword(updatedUser.getPassword());
        newUser.setEmail(updatedUser.getEmail());
        newUser.setYearOfBirth(updatedUser.getYearOfBirth());
        newUser.setRoles(updatedUser.getRoles());
    }

    @Override
    public void delete(long id) {
        session.remove(session.get(User.class, id));
    }
}
