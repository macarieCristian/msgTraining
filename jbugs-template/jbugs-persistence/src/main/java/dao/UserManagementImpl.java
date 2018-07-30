package dao;

import edu.msg.ro.persistence.user.entity.Role;
import edu.msg.ro.persistence.user.entity.User;
import edu.msg.ro.persistence.user.entity.UserStatus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless(name = "UserManagementImpl", mappedName = "UserManagementImpl")
public class UserManagementImpl implements UserManagement {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "jbugs-persistence")
    EntityManager em;

    @Override
    public void addUser(User user) {
        em.persist(user);
    }

    @Override
    public User updateUser(User user) {
        em.merge(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserForUsername(String username) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.username=:username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public void deactivateUser(Long id) {
        User user = em.find(User.class, id);
        user.setStatus(UserStatus.DISABLED);
        em.merge(user);
    }

    @Override
    public void addRole(Role role) {
        em.persist(role);

    }

    @Override
    public void removeRole(Role role) {
        em.remove(role);
    }

    @Override
    public Role updateRole(Role role) {
        em.merge(role);
        return role;
    }

    @Override
    public Role getRoleForId(Long id) {
        TypedQuery<Role> query = em.createQuery("select r from Role r where r.id=:id", Role.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> query = em.createQuery("select r from Role r", Role.class);
        return query.getResultList();
    }
}
