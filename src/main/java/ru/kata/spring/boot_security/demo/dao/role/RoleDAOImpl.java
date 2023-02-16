package ru.kata.spring.boot_security.demo.dao.role;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
@Repository
public class RoleDAOImpl implements RoleDAO {

    private final Session session;
    @Autowired
    public RoleDAOImpl(EntityManager entityManager) {
        session = entityManager.unwrap(Session.class);
    }

    public List<Role> findAll() {
       return new ArrayList<>(session.createQuery("FROM Role", Role.class).getResultList());
    }
    @Override
    public Role findById(long id) {
        return session.get(Role.class, id);
    }

    public Role findByRoleTitle(String roleTitle) {
        Query<Role> roleQuery = session.createQuery("FROM Role R WHERE R.role = :roleTitle", Role.class);
        roleQuery.setParameter("roleTitle", roleTitle);
        Role role = null;
        try {
            role = roleQuery.getSingleResult();
        } catch (NoResultException ignored) {}
        return role;
    }

    @Override
    public Role createIfNotContains(String roleTitle) {
        Role role = findByRoleTitle(roleTitle);
        return role == null ? new Role(roleTitle) : role;
    }


}
