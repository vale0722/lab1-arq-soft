package udea.labs.ejb;

import java.util.List;
import javax.ejb.Local;
import udea.labs.entity.User;

@Local
public interface UserManagerLocal {

    List<User> getAllUsers();

    boolean userExists(Integer id);

    void saveUser(User user);
    
}
