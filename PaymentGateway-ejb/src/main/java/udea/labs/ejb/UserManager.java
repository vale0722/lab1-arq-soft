package udea.labs.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import udea.labs.entity.User;

@Stateless
public class UserManager implements UserManagerLocal {

    @PersistenceContext(unitName = "udea.labs_PaymentGateway-ejb_ejb_1.0-SNAPSHOTP")
    private EntityManager em;

    @Override
    public List<User> getAllUsers() {
        return em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public boolean userExists(Integer id) {
        List<User> users = em.createNamedQuery("User.findById").setParameter("id", id).getResultList();
        return users != null && !users.isEmpty();
    }

    @Override
    public void saveUser(User user) {
        em.merge(user);
    }
}
