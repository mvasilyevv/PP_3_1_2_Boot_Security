package ru.kata.spring.boot_security.demo.dao.user;

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
    public User findByFirstName(String firstName) {
        Query<User> userQuery = session.createQuery("FROM User U WHERE U.firstName = :firstName", User.class);
        userQuery.setParameter("firstName", firstName);
        User user = null;
        try {
            user = userQuery.getSingleResult();
        } catch (NoResultException ignored) {}
        return user;
    }

    @Override
    public User findByEmail(String email) {
        Query<User> userQuery = session.createQuery("FROM User U WHERE U.email = :email", User.class);
        userQuery.setParameter("email", email);
        User user = null;
        try {
            user = userQuery.getSingleResult();
        } catch (NoResultException ignored) {}
        return user;    }

    @Override
    public void save(User user) {
        session.persist(user);
    }

    @Override
    public void update(User updatedUser, long id) {
        User newUser = session.get(User.class, id);
        newUser.setId(updatedUser.getId());
        newUser.setFirstName(updatedUser.getFirstName());
        newUser.setLastName(updatedUser.getLastName());
        newUser.setAge(updatedUser.getAge());
        newUser.setPassword(updatedUser.getPassword());
        newUser.setEmail(updatedUser.getEmail());
        newUser.setRoles(updatedUser.getRoles());
    }

    @Override
    public void delete(long id) {
        session.remove(session.get(User.class, id));
    }
}
